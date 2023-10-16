FROM eclipse-temurin:17-jdk-alpine
LABEL authors="zhvl0"

WORKDIR app

COPY /main/build/libs/main-0.0.1-SNAPSHOT.jar app.jar
COPY /main/src/main/resources/baeldung.p12 baeldung.p12

ARG KEY_PATH=baeldung.p12

ENTRYPOINT ["java","-jar", "app.jar"]