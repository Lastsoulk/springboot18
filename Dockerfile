FROM openjdk:8-jdk-alpine

ADD target/Spring_boot_18-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT  ["java","-jar","Spring_boot_18-0.0.1-SNAPSHOT.jar"] 
