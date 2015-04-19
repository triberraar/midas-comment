# midas-comment
A simple comment website

Build status:

develop: [![build status](https://www.codeship.io/projects/2c11bec0-c5cb-0132-fd48-3a52b81c571d/status?branch=develop)](https://www.codeship.io/projects/74511)

master: [![build status](https://www.codeship.io/projects/2c11bec0-c5cb-0132-fd48-3a52b81c571d/status?branch=master)](https://www.codeship.io/projects/74511)

## Used technologies
### Back-end
The backend is written using Spring boot, a very handy framework to bootstrap webapps.

It contains quite a lot of technologies and the following are used:
* Dependency injection: It is always a good idea to use dependency injection/inversion of control. It makes life for the programmer easy and also makes testing a complex app easier.
* JPA and Spring data: JPA is a enterprise standard to interact with the database. Here the implementation is hibernate. Spring data is a layer on top of JPA to make the interaction with the database easier. It provides an interface that can automatically generate the most common queries (JpaRepository).
* JTA: We use the transactionality provided by spring. No XA is used as we only use one transactional datasource
* Configuration: We use the standard spring bood configuration mechanism to configure the application, for example the data source type.
JAX-RS: Even though Spring provides Spring MVC I choose to use JAX-RS (with jersey as backing implementation) as this is an official Java standard. JSON is used as the data syntax, as this maps great to front-end technologies.
* Liquibase: This is a framework for data migrations.

### Font-end
The front-end is a one page app, written in angularJs. It is one of the hottest and proven technologies used for fron-ends at the moment. Also lodash is used as a helper framework.
The design is based on bootstrap and font-awesome. But as design is not one of my major skills, the design is kept simple.

## Design
### Back-end
The application is designed both horizontally and vertically, the package structure reflects this design. First all related code is grouped together in one package (eg comment) if we would have more separate parts they would all have their own package (eg orders, ...). 

Inside this package we can find 3 layers. Each layer has an api package and an internal package. The code in the api package may be used by other layers, while the code in the internal packages are only to be used inside the package. This gives a good separation between interface and implementation. So the external code doens't need to know the internals.

The 3 layers are as followed:
* domain: This package provides the domain logic, eg the entities, repositories, facades and all related code. The only code that should be used from the outside are the facades as these provide the entry point for modifacations. For query'ing data the repositories may be used.
* services: This is the layer on top of the domain and is mostly used to denote the transactionality. border.
rest: The rest endpoints and json classes live here and provide the interfaces to the external world. This layer uses the domain or the services layer, depending if the rest endpoints mutate and thus need transactionality.

### Front-end
The front end follows the standard design of angular. Interactions with the backend are done using ngResource. The separation between model and view is managed by a Controller.
For the error handling a directive with a service is introduced as this would be reusable for each page.

## Testing
As I am a great advocate of TDD all the code is tested.

### Back-end
Every layer is tested separately. As we test the layer below the currently tested layer, we can assume it works correctly. This leads to tests that are easy to read and refactor, as they don't rely on underlying logics but only on interfaces. 

Integration tests are also provided. They use an in memory database that is reset after each test. This provides certainty that all the parts are wired together correctly and work. These tests only execute in the integration phase as they are rather slow, due to the spring context.

### Front-end
The front end is tested using Karma and Jasmine. Here I test each component separately aswell, for the same reasons as in the back end. The tests are written with help off grunt, a common tool for javascript developers.

To run these tests issue the grunt test command. It is however needed that grunt-cli is installed globally and also a chrome browser, as the chrome browser is used as execution environment.

## Build
To build the application use maven. 
### JAR
When building with maven an executable jar is generated. To execute the jar is java -jar
### WAR
To build the war, provide the profile war. This war can be deployed in tomcat.

## Configuration
To change the configuration change the mysql parameters in the application-prod.yml file.

The current configuratio is as follows:

	url: jdbc:mysql://localhost/midas-test
	username: midas
	password: midas

## Tools
The software is developed using Eclipse. Maven is used for the java build and Bower is used to manage the front-end dependencies. Grunt and npm are used for the front end tests.

The code is continually build upon push (to all branches), in codeship and hosted on github. Git flow was used.