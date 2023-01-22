# ABNAmro-Assessment: Recipe Application API

Recipe Application contains REST APIs which contains Create, Update Get, Delete recipes from the database and send JSON response to user.

### Design
Recipe Application is based on microservices architecture. This module can be deployed independently on premise server, any public cloud and on docker containers. There are 4 layers from top to bottom:
- Controller Layer
- Service Layer
- Entity Layer
- Persistence Layer

## Setup guide

### Minimum Requirements

- Java 17
- Maven 3.x

### Steps to build application
* Download code zip / `git clone https://github.com/VishalHastir/recipe`
* Move to `recipe` and run maven build command `mvn clean package`
* To build by skipping unit tests run maven command `mvn clean package -DskipTests`
* On successfull build completion, one should have jar in `target` directory named as `recipe-0.0.1-SNAPSHOT.jar`
* Open the swagger-ui with http://localhost:8000/swagger-ui/index.html to check all the API documentation and can also try the API's.

### Steps to execute Web Service
* **Execution with Embedded H2 Database**
  - In Development Mode, by default web service uses [Embedded H2 database] for persisting and retrieving recipes details.
  - On successfull start, there will be a log message on console `Tomcat started on port(s): 8000 (http)` and have web service listening for web requests at port 8000.
  - Embedded H2 database will be accessed by using http://localhost:8000/h2-console with username 'sa' and no password.


