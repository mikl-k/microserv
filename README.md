# Spring Boot Demo

## Local run
Just run app from IDE and open [http://localhost:8080](http://localhost:8080)

### How to build
```shell
./gradlew build
```

### Docker Compose
#### Start
```shell
docker compose --project-name="user-demo" up -d
```
And open [http://localhost:8081](http://localhost:8081)

#### Stop
```shell
docker compose --project-name="user-demo" down
```
