FROM openjdk:11
EXPOSE 8080
ADD target/transaction-app-0.0.1-SNAPSHOT.jar transaction-app.jar
ENTRYPOINT ["java", "-jar", "/transaction-app.jar"]