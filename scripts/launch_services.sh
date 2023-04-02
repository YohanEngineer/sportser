#!/bin/bash

osascript -e 'tell application "Terminal" to do script "docker-compose -f /Users/yohan/IdeaProjects/sportser/mongo-configuration/mongo.yaml up"'
osascript -e 'tell application "Terminal" to do script "docker-compose -f /Users/yohan/IdeaProjects/sportser/mysql-configuration/mysql.yaml up"'
osascript -e 'tell application "Terminal" to do script "docker-compose -f /Users/yohan/IdeaProjects/sportser/redis-configuration/redis.yaml up"'
osascript -e 'tell application "Terminal" to do script "/opt/homebrew/opt/mosquitto/sbin/mosquitto -c /opt/homebrew/etc/mosquitto/mosquitto.conf"'
osascript -e 'tell application "Terminal" to do script "cd /Users/yohan/Downloads/kafka_2.13-3.4.0/bin/ && ./zookeeper-server-start.sh ../config/zookeeper.properties"'
osascript -e 'tell application "Terminal" to do script "cd /Users/yohan/Downloads/kafka_2.13-3.4.0/bin/ && ./kafka-server-start.sh ../config/server.properties"'
osascript -e 'tell application "Terminal" to do script "cd /Users/yohan/Downloads/kafka_2.13-3.4.0/bin/ && ./connect-standalone.sh ../config/connect-standalone.properties ../config/mongodb-sink.properties"'
