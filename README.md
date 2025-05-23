# HI This is tris READ THIS
this is an instruction on how to work on the project

when you first cloned the project run:

- change the mongodb config in src/main/resources/application.properties
to fit your local mongodb instance

- then run:
    ./mvnw quarkus:dev
quarkus will do the rest.

- Now you have a running project, go to
http://localhost:8080/q/swagger-ui/
to check if it working properly, this is also the url for testing APIs


Now i will explain how the code work,
all the codes are located in src/main/java/ctu/forum
ALL THE CODES ARE LOCATED IN SRC/MAIN/JAVA/CTU/FORUM

this project follow the REPOSITORY PATTERN

- boundary is the place do define API endpoints, it the outer layer to communicate with the cilent

- dto is Data Transfer Object, these object are to get data from the client without expose db scheme

- interactor/in is to define the Interface for Repository
- interactor/mapper is to map DTO into Model objects to interact with the database

- model is objects in the database schema

- service is where all the logic happen, services implement interfaces

the flow is like this the client send a request with a body to an endpoint define in Boundary, 
-> the Boundary automatically map the request body into the DTO(the body has to have match attributes)
-> the Boundary pass the DTO through a of a Service which has been injected using CDI(think of it as declaring an object then calling it's methods)
-> the Service call the Mapper to map DTO in to Model object (if it a post/put request)
-> then the Service perform logic(read or write to database)
-> after that the Service return results and the Boundary build a response for the client.
# ctu-forum

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/ctu-forum-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- MongoDB with Panache ([guide](https://quarkus.io/guides/mongodb-panache)): Simplify your persistence code for MongoDB via the active record or the repository pattern
