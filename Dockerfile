FROM openjdk:11-jre-slim
MAINTAINER "Ramazan Sakin"
ADD target/vocid22-tracker-application.jar vocid22-tracker-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vocid22-tracker-application.jar"]