FROM openjdk:8-jdk-alpine

COPY ${JENKINS_HOME}\workspace\springboot18\target\Spring_boot_18-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","Spring_boot_18-0.0.1-SNAPSHOT.jar"]
