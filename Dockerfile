FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

COPY --from=build /target/ToDoList-0.0.1-SNAPSHOT.jar Ecom.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "Ecom.jar"]
