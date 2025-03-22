
Install packages:
```
 pip install -r requirements.txt
```

```
source .venv/bin/activate
```

### Build Docker Image

````aiignore
docker build -t newsfeed-crawler .
````

### Running Docker container
```
docker run -d --name newsfeed-crawler -e S3_BUCKET=my-bucket-name newsfeed-crawler
```