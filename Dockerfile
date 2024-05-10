FROM openjdk:17-jdk-alpine
COPY target/spacecraft-api-0.0.1.jar spacecraft-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/spacecraft-api-0.0.1.jar"]