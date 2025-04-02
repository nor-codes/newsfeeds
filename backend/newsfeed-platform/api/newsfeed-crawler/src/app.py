from src.download_util import DownloadUtil
from src.s3_content_util import S3ContentUtil
from datetime import datetime
from flask import Flask
import logging
import uuid
import json
import os
import boto3

app = Flask(__name__)
# sources dictionary {name:url}
logging.basicConfig(
    level=logging.INFO,  # Set logging level
    format="%(asctime)s - %(levelname)s - %(message)s",  # Define log format
)

logger = logging.getLogger(__name__)

sources = {
    "bbc-rss":"https://feeds.bbci.co.uk/news/rss.xml"
}

bucket_name = os.getenv("S3_BUCKET")
sqs_queue_url = os.getenv("SQS_QUEUE_URL")
is_development = os.getenv("IS_DEVELOPMENT")

def send_sqs_message(object_key):
    print(sqs_queue_url)
    message_body = {
        "id": str(uuid.uuid4()),
        "time-stamp":f"{datetime.now().strftime('%Y-%m-%d %H:%M:%s')}",
        "object-id":object_key,
        "s3-bucket":bucket_name
    }

    sqs_client = boto3.client('sqs')

    sqs_client.send_message(
        QueueUrl = sqs_queue_url,
        MessageBody = json.dumps(message_body)
    )
    logger.info("message send to SQS")

def scrape():
    logging.info(f"Job starting")
    for source in sources:
        url = sources[source]
        response = DownloadUtil.download(url)
        client = boto3.client('s3')
        s3_util = S3ContentUtil(client)
        file_date = datetime.now().strftime('%Y-%m-%d %H:%M')
        object_key = f"{source}/rrs-{file_date}.xml"
        s3_util.putObject(bucket_name, object_key, response.payload)
        send_sqs_message(object_key)

    logger.info(f"Job finished")

@app.route("/crawl",methods=["POST"])
def crawl():
    logger.info(f"Starting scrapping")
    scrape()
    logger.info(f"Finished scrapping")

@app.route("/")
def health():
    logger.info(f"Health Check")
    return "I'm healthy..."

if __name__ == "__main__":
    app.run()