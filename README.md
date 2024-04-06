## Microservice architecture leveraging CQRS and Event Sourcing for robust event-driven communication and data consistency - 


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
