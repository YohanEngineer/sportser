#We execute those commands on the second node. (The docker run command is the same for the first node (except names))
docker run --name rabbit2 --hostname rabbit2 -d --network host -p 5672:5672 -p 15672:15672 -v rabbitmq_data:/var/lib/rabbit2 -e RABBITMQ_ERLANG_COOKIE=samecookie rabbitmq:3.8-management

echo "192.168.1.9 rabbit1" >> /etc/hosts

docker exec -it rabbit2 bash

rabbitmqctl start_app

rabbitmqctl reset

rabbitmqctl join_cluster rabbit@rabbit1

rabbitmqctl start_app