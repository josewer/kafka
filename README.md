
# Proyecto Kafka con Spring Boot 🧩

Este proyecto está compuesto por dos microservicios independientes que se comunican a través de **Apache Kafka**:

- `SpringBootProviderApplication`: envía mensajes a Kafka.
- `SpringBootConsumerApplication`: recibe y procesa mensajes desde Kafka.

---

## 🧱 Requisitos previos

Antes de ejecutar los microservicios, asegúrate de tener un **servidor de Kafka** y **Zookeeper** en ejecución.

Puedes levantar Kafka de dos formas:

---

## Opción 1: Kafka en Windows (local)

### ▶️ Iniciar Zookeeper

```bash
.in\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

### ▶️ Iniciar Kafka

```bash
.in\windows\kafka-server-start.bat .\config\server.properties
```

### ▶️ Crear un nuevo topic en el servidor Kafka

```bash
.in\windows\kafka-topics.bat --create --topic {topic-name} --bootstrap-server {host}:9092
```

### ▶️ Describir un topic

```bash
.in\windows\kafka-topics.bat --describe --topic {topic-name} --bootstrap-server {host}:9092
```

### ▶️ Listar todos los topics

```bash
.in\windows\kafka-topics.bat --list --bootstrap-server {host}:9092
```

### ▶️ Ver mensajes de un topic (modo consola)

```bash
.in\windows\kafka-console-consumer.bat --topic {topic-name} --bootstrap-server {host}:9092
```

### ▶️ Enviar mensajes a un topic (modo consola)

```bash
.in\windows\kafka-console-producer.bat --broker-list {host}:9092 --topic {topic-name}
```

---

## Opción 2: Kafka + Zookeeper con Docker 🐳

Si prefieres levantar los servicios con Docker:

### ▶️ docker-compose.yml

```yaml
version: '2'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.2.1
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:7.2.1
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

Levantar con:

```bash
docker-compose up -d
```

---

## 🚀 Cómo ejecutar el proyecto

### 1. Clona el repositorio

```bash
git clone <url-del-repo>
cd <nombre-del-proyecto>
```

### 2. Ejecuta los microservicios

Abre dos terminales o IDEs separados y ejecuta:

- `SpringBootProviderApplication`
- `SpringBootConsumerApplication`

---

## ⚙️ Configuración recomendada

### 📝 `application.properties` (para ambos microservicios)

#### Producer (`SpringBootProviderApplication`)
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
```

#### Consumer (`SpringBootConsumerApplication`)
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=grupo-demo
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

---

## 🧬 Comunicación entre microservicios

1. `SpringBootProviderApplication` produce un mensaje a un **topic Kafka**.
2. `SpringBootConsumerApplication` está suscrito a ese topic y lo procesa automáticamente.

Asegúrate de que ambos usen el mismo **topic name**.

---

## 🧪 Verificación rápida

Usa los comandos de consola para ver si los mensajes están llegando correctamente. También puedes añadir logs en tus consumidores/proveedores para verificar el flujo de datos.

---

## 📚 Recursos útiles

- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [Spring Kafka Reference](https://docs.spring.io/spring-kafka/docs/current/reference/html/)
- [Spring Boot Kafka Guide](https://spring.io/projects/spring-kafka)

