cd scripts
docker-compose --file config.yaml up -d
docker-compose --file startup.yaml up -d
docker exec -it mongodb /bin/bash
cd bin
mongosh -u rootuser -p rootpass
use sportser
db.getCollectionNames()
cd sportser-mock
python3 main.py
db.getCollectionNames()
db.users.find()
db.getCollection("hr-data-collector").find().sort({ time: -1 }).forEach((document) => { const timestamp = new Date(document.time.toNumber()); printjson({ ...document, time: timestamp.toISOString() }); });
db.getCollection("emergency-data-collector).find().sort({ time: -1 }).forEach((document) => { const timestamp = new Date(document.time.toNumber()); printjson({ ...document, time: timestamp.toISOString() }); });
db.getCollection("notification-channel").find().sort({ time: -1 }).forEach((document) => { const timestamp = new Date(document.time.toNumber()); printjson({ ...document, time: timestamp.toISOString() }); });

docker exec -it kafka /bin/bash
cd /opt/kafka/bin/

./kafka-topics.sh \
	--list \
	--bootstrap-server \
	localhost:9092

./kafka-console-consumer.sh \
		--topic hr-data-collector \
		--offset latest \
		--timeout-ms 5000 \
		--bootstrap-server \
		localhost:9092
