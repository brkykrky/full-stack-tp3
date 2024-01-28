FROM maven:3.8.4 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17
COPY --from=build /app/target/shop-app-0.0.1-SNAPSHOT.jar /shop-app.jar
ENTRYPOINT ["java", "-jar", "/shop-app.jar"]
EXPOSE 8080
