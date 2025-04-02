FROM python:3.13-bullseye

WORKDIR /app

COPY requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

COPY . .

# Ensure Python recognizes the `src` directory
ENV PYTHONPATH=/app

CMD ["flask", "run", "--host=0.0.0.0"]
