# Lapinsimu
Reproduit le comportement biologique d'un lapin pour une école vétérinaire.

## Plan du code :

Dans Javascript
	-graph.js
	la génération du graph pour l'affichage.
	am4theme

	-http_server.js
	le server a lancer, communique entre la DB et l'affichage.

	-affichage.html
	la page a afficher pour voir le résultat

java/Lapin/src/
	generationDonnees/
		Main.java
		la génération des données

	database/
		scribe.java
		l'object qui ecrit dans la DB
		TestDB.java
		les test unitaires de scribe

les files 'runall..' sont suposé permettre un lancement en un clique de tout le système.
Ces fichiers ne sont pas compléter!

docker-compose.yml
se lance seul avec 
	'docker-compose up'
ou 'docker-compose.yml up'
démare le docker Lapin de influxDB

Demander a Charle Prudhome pour faire un docker avec Java 