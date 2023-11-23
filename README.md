# Spring Boot Demo

### How to build (with all tests)
```shell
./gradlew build
```

### Docker Compose
#### Create /tmp/users-data for data persistence
```shell
mkdir /tmp/users-data
```
#### Start
```shell
docker compose --project-name="user-demo" up -d
```
And open [http://localhost:8081](http://localhost:8081)

#### Stop
```shell
docker compose --project-name="user-demo" down
```
