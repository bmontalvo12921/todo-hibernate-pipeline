FROM eclipse-temurin:23-jdk
WORKDIR /app
COPY target/todo-hibernate-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]