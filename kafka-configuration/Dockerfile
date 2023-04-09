FROM openjdk:11-jre-slim

COPY kafka_2.13-3.4.0 /opt/kafka

EXPOSE 9092 2181

COPY start-kafka-zookeeper.sh /opt/

RUN chmod +x /opt/start-kafka-zookeeper.sh

ENTRYPOINT ["/opt/start-kafka-zookeeper.sh"]
