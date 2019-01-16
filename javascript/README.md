
Pour lancer tout ca, un docker est disponible,
lancer la commande :
    docker build -t lapin .
Puis 
    docker run -p 8086:8086 -p 3000:3000 lapin

pour InfluxDB :
    docker run -p 8086:8086 \
    -v $PWD:/var/lib/influxdb \
    influxdb

Lancer Influx Avant Lapin!
Je vais faire un docker-compose a un moment

note:
docker rm $(docker ps -aq)
