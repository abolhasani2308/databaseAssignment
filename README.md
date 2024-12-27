# Database Assignment Project

Author: Amir Mohammad Abolhasani
Linkedin: https://www.linkedin.com/in/a-abolhasani/

This project demonstrates the usage of Hibernate ORM for managing database operations in a Java application. It includes basic functionality to interact with entities such as `Product`, `Category`, `Customer`, and `Orders`.

## Features
- Hibernate ORM for database interaction
- H2 in-memory database for quick testing
- CRUD operations for `Product`, `Category`, `Customer`, and `Orders` entities
- Example of establishing relationships between entities

## Technologies Used
- **Java 17**
- **Hibernate 5.6.5.Final**
- **H2 Database 2.1.214**
- **Maven** for dependency management

## Setup and Usage
1.Clone the Repository:
git clone https://github.com/abolhasani2308/databaseAssignment.git
cd databaseAssignment
2.Build the Project:
mvn clean install
mvn clean compile
3.Run the Application:
mvn exec:java -Dexec.mainClass="com.databaseassignment.Main"
4.Check Output
