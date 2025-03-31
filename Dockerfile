FROM openjdk:21
ADD build/libs/*.jar docker-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-application.jar"]