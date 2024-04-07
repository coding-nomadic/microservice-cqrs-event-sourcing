## Microservice design pattern, CQRS, Event Sourcing, Kakfa and SQL/NoSQL - 


Discover how to construct distributed event-driven microservices, employing CQRS, Event Sourcing, and Kafka, along with PostGreSQL and MongoDB databases. 

- Implement CQRS to separate commands for modifying state and queries for reading data.
- Use Event Sourcing to persist state changes as events in an event store.
- Integrate Kafka for event distribution between system components.
- Command handlers validate commands and generate domain events, which are published to Kafka.
- Event handlers consume events from Kafka, update the system's read and write models accordingly.

## Concepts applied -  
- Event-Driven Microservices
- CQRS Design Pattern
- Event Based Messages
- Kafka
- NoSQL database with MongoDB
- SQL database with PostGreSQL

## Swagger API endpoints - 

![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/3c9f48ba-8a9a-465e-a65c-83460d05f668)


![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/1d75b441-ea4a-4f57-8e43-a2a6cb22f3c4)


## Resource -  

Project is based on Udemy course - https://www.udemy.com/course/java-microservices-cqrs-event-sourcing-with-kafka/
