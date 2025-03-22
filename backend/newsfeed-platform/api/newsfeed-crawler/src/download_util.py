import logging
from datetime import datetime

import requests
from src.models.response import Response

class DownloadUtil:

    @staticmethod
    def download(url):
        logging.basicConfig(
            level=logging.INFO,  # Set logging level
            format="%(asctime)s - %(levelname)s - %(message)s",  # Define log format
        )
        response = requests.get(url)
        content = response.content.decode('utf-8')
        print(f"Finished downloading from {url}")
        return Response(response.status_code, content)
