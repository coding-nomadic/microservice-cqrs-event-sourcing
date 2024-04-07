## Microservice CQRS Event Sourcing - 


Explore constructing distributed microservices with CQRS, Event Sourcing, Kafka, and SQL/NoSQL databases:

- Implement CQRS to separate commands for modifying state and queries for reading data.
- Use Event Sourcing to persist state changes as events in an event store.
- Integrate Kafka for event distribution between system components.
- Command handlers validate commands and generate domain events, which are published to Kafka.
- Event handlers consume events from Kafka, update the system's read and write models accordingly.

## Architecture - 

![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/1af6a3dc-dc94-4703-9603-2f981df25cdc)

## Concepts applied -  

- Event-Driven Microservices
- CQRS Design Pattern
- Event Based Messages
- Kafka
- NoSQL database with MongoDB
- SQL database with PostGreSQL

## Swagger API endpoints - 

![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/3c9f48ba-8a9a-465e-a65c-83460d05f668)


![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/e0771b2b-c5a6-47c5-89fe-417b59a0d691)


## Command to start the zookeeper -

- bin/zookeeper-server-start.sh config/zookeeper.properties

## Start Kafka server -

- bin/kafka-server-start.sh config/server.properties

## Below kafka topics with each 3 partitions -

- OPEN-BANK-ACCOUNT
- DEPOSIT-FUND-ACCOUNT
- CLOSE-BANK-ACCOUNT
- WITHDRAW-FUND-ACCOUNT

## Resource -  

Project is based on Udemy course - https://www.udemy.com/course/java-microservices-cqrs-event-sourcing-with-kafka/
