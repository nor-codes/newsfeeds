from botocore.exceptions import ClientError
import logging
class S3ContentUtil:

    def __init__(self, s3_client):
        self.s3 = s3_client

    def putObject(self,bucket,key,content):
        try:
            self.s3.put_object(
                Bucket=bucket, Key=key, Body=content
            )
            logging.error(
                f"\tConditional write successful for key {key} in bucket {bucket}."
            )
        except ClientError as e:
            error_code = e.response["Error"]["Code"]
            if error_code == "PreconditionFailed":
                logging.error("\tConditional write failed: Precondition failed")
            else:
                logging.error(f"Unexpected error: {error_code}")
                raise