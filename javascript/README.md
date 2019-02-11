
Pour lancer tout ca, un docker est disponible,
lancer la commande :
    docker build -t lapin .
Puis 
    docker run -p 8086:8086 -p 3000:3000 lapin

sans docker il faut juste lancer :
    node http_server.js
a la condition d'avoir node evidement

pour InfluxDB 1 :
    docker run -p 8086:8086 \
    -v $PWD:/var/lib/influxdb \
    influxdb

ou 2:
    docker run -p 8086:8086 \
    influxdb

la différence est ue dans le premier cas les donnée sont sauvegardées, utile en prod pas en test.

Lancer Influx Avant Lapin!

note:
    docker rm $(docker ps -aq)

quand ous avez fini on arréte voir enlève les docker que l'on avait lancer.
remarque, eteindre son ordinateur le fait également.


/home/guliver/workingD/Lapinsimu/javascript