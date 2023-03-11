#Video Libarary 2

This project is the second version of Video Library products. The first project was written using serlvets.
###The technology stack used in this project
- Java 17
- SpringBoot
- Postgres + Liquibase
- Maven
- Thymeleaf

##Prerequisites
- Java 17

##Start the server:
For local run, you can start Postgres DB in docker container. Don't forget to execute `docker rm <continer id>` 
after stopping application server.

For running server locally, execute the following commands:
```
./mvnw clean install -Dmaven.test.skip=true 
docker run --name p1 -p 5433:5432 -e POSTGRES_PASSWORD=pass -d postgres
java -jar service/target/video-library-2.jar
```

1. Check existing projects that used k8s before
2. 
