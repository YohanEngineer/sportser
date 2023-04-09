# Use ubuntu:20.04 as the base image
FROM ubuntu:20.04

# Set non-interactive mode for apt-get
ENV DEBIAN_FRONTEND=noninteractive

# Update package list and install required tools
RUN apt-get update && \
    apt-get install -y wget curl gnupg software-properties-common openjdk-11-jre-headless && \
    rm -rf /var/lib/apt/lists/*

# Add the MongoDB public key and repository
RUN wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | apt-key add - && \
    echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-4.4.list

# Update package list and install MongoDB shell
RUN apt-get update && \
    apt-get install -y mongodb-org-shell && \
    rm -rf /var/lib/apt/lists/*

# Copy Kafka
COPY kafka_2.13-3.4.0 /opt/kafka

# Expose ports
EXPOSE 9092 2181

# Copy start script
COPY start-kafka-zookeeper.sh /opt/

# Make the script executable
RUN chmod +x /opt/start-kafka-zookeeper.sh

# Set the entrypoint
ENTRYPOINT ["/opt/start-kafka-zookeeper.sh"]
