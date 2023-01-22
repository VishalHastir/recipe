# ABNAmro-Assessment: Recipe Application API

Recipe Application contains REST APIs which contains Create, Update Get, Delete recipes from the database and send JSON response to user.

### Design
Recipe Application is based on microservices architecture. This module can be deployed independently on premise server, any public cloud and on docker containers. There are 4 layers from top to bottom:
- Controller Layer
- Service Layer
- Entity Layer
- Persistence Layer

### Setup guide

#### Minimum Requirements

- Java 17
- Maven 3.x

### Steps to build application
* Download code zip / `git clone https://github.com/VishalHastir/recipe`
* Move to `recipe` and run maven build command `mvn clean package`
* To build by skipping unit tests run maven command `mvn clean package -DskipTests`
* On successfull build completion, one should have jar in `target` directory named as `recipe-0.0.1-SNAPSHOT.jar`
* Open the swagger-ui with http://localhost:8000/swagger-ui/index.html to check all the API documentation and can also try the API's.


