## spring-boot-kafka
 configure carpeta logs
 por defecto esta configurado una ruta en linux para guardar los logs
## KAFKA
 buscar dentro de la ruta /config/server.properties
log.dirs=/tmp/kafka-logs   reemplazar con la ruta de windows
## ZOOKIPER
/config/zookeeper.properties
buscar dataDir=/tmp/zookeeper reemplazar con la ruta de windows



 Comandos Zookeeper y Kafka:

### Iniciar Zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

### Iniciar Kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties

### Crea un nuevo topic en el servidor de kafka
.\bin\windows\kafka-topics.bat --create --topic {topic-name} --bootstrap-server {host}:9092

### Decribir los detalles de un topic
.\bin\windows\kafka-topics.bat --describe --topic {topic-name} --bootstrap-server {host}:9092

### Listar todos los topics que existen dentro del broker
.\bin\windows\kafka-topics.bat --list --bootstrap-server {host}:9092

### Inicia una consola para ver mensajes de un topic específico - CONSUMER
.\bin\windows\kafka-console-consumer.bat --topic {nombreTopic} --bootstrap-server {host}:9092

### Inicia una consola para enviar mensajes a un topic específico - PRODUCER
.\bin\windows\kafka-console-producer.bat --broker-list {host}:9092 --topic {topic-name}



##### reference 
https://www.youtube.com/watch?v=UbbyW5Z1lv8
