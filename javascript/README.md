
Pour lancer tout ca, un docker est disponible,
lancer la commande :
    docker build .


pour InfluxDB :
    docker run -p 8086:8086 \
    -v $PWD:/var/lib/influxdb \
    influxdb

Un 
    

    docker run --name movie-service -p 3000:3000 -e DB_SERVERS="192.168.99.100:27017 192.168.99.101:27017 192.168.99.100:27017" -d movies-service