FROM jelastic/maven:3.9.5-openjdk-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
VOLUME /tmp
COPY --from=build /app/target/swordsNstuffAPI-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 6969
ENTRYPOINT ["java", "-jar", "/app.jar"]