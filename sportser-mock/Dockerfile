FROM python:3.9.7-slim-buster

COPY main.py /app/main.py

WORKDIR /app

RUN pip install requests

ENTRYPOINT [ "python", "main.py"]