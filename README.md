# Spring Boot Demo

## Run in Docker
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
