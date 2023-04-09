#!/bin/bash
set -e

cd /opt/kafka/bin/

# Démarrer Zookeeper en arrière-plan
sh zookeeper-server-start.sh ../config/zookeeper.properties &

# Démarrer Kafka en arrière-plan
sh kafka-server-start.sh /opt/kafka/config/server.properties &

until mongosh --host mongodb -u rootuser -p rootpass --quiet --eval "db.adminCommand('ping')"
do
    echo 'Waiting for MongoDB to start...'
    sleep 1
done

# Démarrer le connecteur MongoDB en arrière-plan
sh connect-standalone.sh ../config/connect-standalone.properties ../config/mongodb-sink.properties &

# Attendre indéfiniment pour empêcher le conteneur de s'arrêter
tail -f /dev/null