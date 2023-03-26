## Redirecting to the RabbitMQ Web Interface
sudo iptables -t nat -A PREROUTING -p tcp --dport 15675 -j DNAT --to-destination 192.168.1.9:15672
sudo iptables -t nat -A POSTROUTING -p tcp -d 192.168.1.9 --dport 80 -j SNAT --to-source 192.168.1.1

## Redirecting to the server discovery
sudo iptables -t nat -A PREROUTING -p tcp --dport 8761 -j DNAT --to-destination 192.168.1.2:8761
sudo iptables -t nat -A POSTROUTING -p tcp -d 192.168.1.2 --dport 8761 -j SNAT --to-source 192.168.1.1

## Redirecting to the zuul server
sudo iptables -t nat -A PREROUTING -p tcp --dport 9004 -j DNAT --to-destination 192.168.1.2:9004
sudo iptables -t nat -A POSTROUTING -p tcp -d 192.168.1.2 --dport 9004 -j SNAT --to-source 192.168.1.1

## Redirecting to the gitlab server
sudo iptables -t nat -A PREROUTING -p tcp --dport 8929 -j DNAT --to-destination 192.168.1.30:80
sudo iptables -t nat -A POSTROUTING -p tcp -d 192.168.1.2 --dport 80 -j SNAT --to-source 192.168.1.1

## Redirecting to the mqtt server
sudo iptables -t nat -A PREROUTING -p tcp --dport 1883 -j DNAT --to-destination 192.168.1.40:1883
sudo iptables -t nat -A POSTROUTING -p tcp -d 192.168.1.40 --dport 1883 -j SNAT --to-source 192.168.1.1