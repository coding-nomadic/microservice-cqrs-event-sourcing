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


## Service	EndPoint	Method	Description -

- Account Command	- /api/v1/accounts	- POST	- Open an account
- Account Command	- /api/v1/accounts/{id}/deposit	- PUT	- Deposit funds
- Account Command	/api/v1/accounts/{id}/withdraw	PUT	Withdraw funds
- Account Command	/api/v1/accounts/{id}	DELETE	Close an account
- Account Query	/api/v1/accounts	GET	List of accounts
- Account Query	/api/v1/accounts/{id}	GET	LIst of accounts

| Method     | Endpoint |
| POST        |               |
| PUT   |               |
| PUT       |              |

## Swagger API endpoints - 

![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/3c9f48ba-8a9a-465e-a65c-83460d05f668)


![image](https://github.com/coding-nomadic/microservice-cqrs-event-sourcing/assets/8009104/e0771b2b-c5a6-47c5-89fe-417b59a0d691)



## Resource -  

Project is based on Udemy course - https://www.udemy.com/course/java-microservices-cqrs-event-sourcing-with-kafka/
