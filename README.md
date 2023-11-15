# Spring Boot Demo

## Local run
Just run app from IDE and open [http://localhost:8080](http://localhost:8080)

## Run in Docker
### How to build
```shell
./gradlew dockerBuildImage
```

### Docker Compose
#### Start
```shell
docker compose --project-name="spring-boot-demo" up -d
```
And open [http://localhost:8080](http://localhost:8080)

#### Stop
```shell
docker compose --project-name="spring-boot-demo" down
```