# Videogames Service

Proyecto de microservicios usando spring boot, mongodb, apache kafka, y despliegue en contenedores con docker.

## Entornos de ejecución

---

### Entorno local sin docker:

Necesario `Kafka server` `Maven` `Java 11` `MongoDB server`

#### Para arrancar zookeeper y kafka server (desde cmd en la carpeta de kafka)

    bin\windows\zookeeper-server-start.bat config\zookeeper.properties

    bin\windows\kafka-server-start.bat config\server.properties

#### Para crear las colas (tener zookeeper y kafka server arrancados)

    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --if-not-exists --topic new-game-request-topic

    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --if-not-exists --topic new-game-response-topic

---

### Entorno local con docker

Necesario `Docker`

#### Para arrancar los servicios de kafka y las bases de datos mongo (desde cmd en la carpeta del proyecto)

	 docker-compose -f docker\docker-compose-dev.yaml up --build
	 
#### Para parar los contenedores cerrar el cmd, y para eliminarlos:

	 docker-compose -f docker\docker-compose-dev.yaml down
	 
### Despliegue de los contenedores (desde cmd en la carpeta del proyecto)

	docker-compose -f docker\docker-compose-prod-<version>.yaml up --build