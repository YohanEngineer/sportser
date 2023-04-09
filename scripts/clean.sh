cd /Users/yohan/Downloads/kafka_2.13-3.4.0/bin

./kafka-topics.sh \
        --delete \
        --topic hr-data-collector \
        --bootstrap-server \
        localhost:9092

./kafka-topics.sh \
        --delete \
        --topic emergency-data-collector \
        --bootstrap-server \
        localhost:9092


./kafka-topics.sh \
        --delete \
        --topic notification-channel \
        --bootstrap-server \
        localhost:9092