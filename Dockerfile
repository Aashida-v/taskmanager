FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

<<<<<<< HEAD
ENTRYPOINT ["sh","-c","java -jar app.jar --server.port=$PORT"]
=======
ENTRYPOINT ["sh","-c","java -jar app.jar --server.port=$PORT"]
>>>>>>> 7f6c821 (Fixed Firebase initialization issue)
