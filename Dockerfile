FROM openjdk:17-jdk-alpine
COPY target/spacecraft-0.0.1-SNAPSHOT.jar spacecraft-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/spacecraft-0.0.1-SNAPSHOT.jar"]