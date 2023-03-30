FROM gradle:7.6.0-jdk19-focal as build
WORKDIR /app
COPY / .
RUN chmod +x ./gradlew
RUN gradle bootJar

FROM openjdk:19-jdk-slim-buster
EXPOSE 8080
RUN mkdir /app

COPY --from=build /app/build/libs/*.jar application.jar

CMD ["java", "-jar", "application.jar"]