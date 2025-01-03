# Database Assignment Project

Author: Amir Mohammad Abolhasani<br/>
Student ID: 403139232<br/>
Linkedin: https://www.linkedin.com/in/a-abolhasani/<br/>

This project is a Java-based application that demonstrates the use of Hibernate ORM to interact with a relational database. The project focuses on CRUD operations with entities such as `Category`, `Product`, `Customer`, and `Orders`.<br/>

## Project Structure
`Main.java`<br/>
This is the entry point of the application. It initializes Hibernate, creates entities, and performs CRUD operations. It also demonstrates the use of services and repositories to manage customers.<br/>
`Entities`<br/>
Category.java: Represents the category of products.<br/>
Product.java: Represents products that belong to categories.<br/>
Customer.java: Represents customers who place orders.<br/>
Orders.java: Represents orders placed by customers.<br/>
`Repositories`<br/>
These classes are responsible for interacting with the database using Hibernate.<br/>
BaseRepository.java: A generic repository for database operations.<br/>
CategoryRepository.java: Repository for performing CRUD operations on categories.<br/>
CustomerRepository.java: Repository for performing CRUD operations on customers.<br/>
OrdersRepository.java: Repository for performing CRUD operations on orders.<br/>
ProductRepository.java: Repository for performing CRUD operations on products.<br/>
`Services`<br/>
Service classes encapsulate business logic and interact with the repositories.<br/>
CategoryService.java: Service layer for category-related operations.<br/>
CustomerService.java: Service layer for customer-related operations.<br/>
OrdersService.java: Service layer for orders-related operations.<br/>
ProductService.java: Service layer for product-related operations.<br/>
`Resources`<br/>
hibernate.cfg.xml: Hibernate configuration file, which includes database connection settings and entity mappings.<br/>
hibernate.reveng.xml: Hibernate reverse engineering file, which specifies tables to be considered for entity generation.<br/>
log4j2.xml: Log4j2 configuration file to set up logging.<br/>
`pom.xml`<br/>
Maven configuration file that manages project dependencies, plugins, and build settings.<br/>

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
- **Log4j 2.20.0** for logging

## Setup and Usage
1.Clone the Repository:<br/>
git clone https://github.com/abolhasani2308/databaseAssignment.git<br/>
cd databaseAssignment<br/>
2.Build the Project:<br/>
mvn clean install compile<br/>
3.Run the Application:<br/>
mvn exec:java -Dexec.mainClass="com.databaseassignment.Main"<br/>
