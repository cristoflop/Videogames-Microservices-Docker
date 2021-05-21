# KafkaPractica

Proyecto de microservicios usando spring boot, mongodb, apache kafka, y despliegue en contenedores con docker

## Necesario tener instalado:

`Kafka server`
`Maven`
`Java 11`
`MongoDB server`
`MySql server`

#### Para arrancar zookeeper y kafka server (desde cmd en la carpeta de kafka)

    bin\windows\zookeeper-server-start.bat config\zookeeper.properties

    bin\windows\kafka-server-start.bat config\server.properties

#### Para crear las colas (tener zookeeper y kafka server arrancados)

    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --if-not-exists --topic new-game-request-topic

    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --if-not-exists --topic new-game-response-topic

## Autores

---

- [@danielDiz](https://github.com/danielDiz)
- [@davidgarciamontoto](https://github.com/davidgarciamontoto)
- [@cristoflop](https://github.com/cristoflop)