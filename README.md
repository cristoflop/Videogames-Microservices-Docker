# KafkaPractica

Proyecto de microservicios usando spring boot, mongodb, apache kafka, y despliegue en contenedores con docker

## Necesario tener instalado:

`Kafka`
`Maven`
`Java 11`

#### Para crear las colas

    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --if-not-exists --topic new-game-request-topic

    bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --if-not-exists --topic new-game-response-topic

## Autores

---

- [@danielDiz](https://github.com/danielDiz)
- [@davidgarciamontoto](https://github.com/davidgarciamontoto)
- [@cristoflop](https://github.com/cristoflop)