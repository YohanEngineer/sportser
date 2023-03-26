FROM openjdk:17-jdk-slim

EXPOSE 8761

ADD target/sportser-server-discovery.jar sportser-server-discovery.jar

ENTRYPOINT [ "java", "-jar", "sportser-server-discovery.jar"]
