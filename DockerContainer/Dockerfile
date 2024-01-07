FROM openjdk:17

WORKDIR /app
COPY OracleTest-1.0-SNAPSHOT.jar /app/Application.jar
COPY webApp.yml /app/webApp.yml

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Application.jar", "server", "webApp.yml"]
