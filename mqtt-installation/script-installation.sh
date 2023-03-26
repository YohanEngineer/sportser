apt-get install mosquitto mosquitto-clients

## start mosquitto with our config
mosquitto -c /home/toto/configMosquitto.conf -v
systemctl start mosquitto


# with publisher
# mosquitto_pub -h localhost -t test -m "hello world"

# with subscriber
#mosquitto_sub -h localhost -t test