FROM openjdk:17-jdk-slim

EXPOSE 8081

ADD target/sportser-heart-rate-sensor-data-collector.jar sportser-heart-rate-sensor-data-collector.jar

ENTRYPOINT [ "java", "-jar", "sportser-heart-rate-sensor-data-collector.jar"]
