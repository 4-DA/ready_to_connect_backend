# Use Python base image
FROM python:3.13.1-slim-bullseye

# Install curl 
RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

# Set environment variables

# Turns off an automatic check for pip updates each time
ENV PIP_DISABLE_PIP_VERSION_CHECK 1 
# Python will not try to write .pyc files
ENV PYTHONDONTWRITEBYTECODE 1
# Ensures Docker does not buffer our console output
ENV PYTHONUNBUFFERED 1

# Set working directory
WORKDIR /code

# Copy requirements
COPY docker/requirements.txt /code/

# Install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy project files
COPY . /code/