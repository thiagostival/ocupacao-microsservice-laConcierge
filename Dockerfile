FROM maven:3.8.6-openjdk-18 AS build
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn install

FROM openjdk:18
EXPOSE 8080
COPY --from=build /usr/src/app/target/ocupacao-0.0.1-SNAPSHOT.jar /usr/app/app.jar
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
