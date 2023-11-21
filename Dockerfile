FROM openjdk:17
LABEL authors="mikl"
RUN mkdir /app
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} /app/application.jar
EXPOSE 8081
WORKDIR /app
ENTRYPOINT ["java", "-jar", "application.jar"]
