# MomoPetshop – Backend API

MomoPetshop is a **Spring Boot–based Backend REST API** for a pet shop management system. This project is developed for **backend and DevOps practice**, with a focus on API development, local environment setup, database integration, and API documentation.

The application runs entirely in a **local environment**. Both the backend service and the MySQL database are executed locally on the developer’s machine using an active local profile.

This project **does not implement authentication or authorization** (no login or JWT). All API endpoints can be accessed and tested directly using **Swagger**.

Database management is handled using **Spring Data JPA with Hibernate**. With the configuration `ddl-auto: update`, database tables are **automatically generated and updated** based on the defined JPA entities. No manual SQL schema scripts are required; only an empty MySQL database needs to be created before running the application.



## Tech Stack

* Java 25
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger (Springdoc OpenAPI)
* Git & GitHub



## API Documentation

Swagger UI is available locally at:

```
http://localhost:8080/swagger-ui/index.html
```

All endpoints can be tested directly through Swagger without authentication.


## How to Run (Local)

1. Clone the repository:

   ```bash
   git clone https://github.com/username/momopetshop.git
   cd momopetshop
   ```

2. Create an empty MySQL database:

   ```sql
   CREATE DATABASE db_petshopkasir;
   ```

3. Configure the database connection in `application.yaml` or `application-local.yaml`.

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

5. Open Swagger UI in the browser to test the API.



## Notes

* The application runs fully in a **local environment**
* Database tables are generated automatically using JPA and Hibernate
* No authentication or security layer is implemented
* Suitable for backend learning, DevOps practice, and portfolio usage
