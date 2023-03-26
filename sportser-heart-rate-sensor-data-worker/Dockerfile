FROM openjdk:17-jdk-slim

EXPOSE 8082

ADD target/sportser-heart-rate-sensor-data-worker.jar sportser-heart-rate-sensor-data-worker.jar

ENTRYPOINT [ "java", "-jar", "sportser-heart-rate-sensor-data-worker.jar"]
