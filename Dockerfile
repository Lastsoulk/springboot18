FROM openjdk:8-jdk-alpine
cd C:\Program Files (x86)\Jenkins\workspace\springboot18
COPY C:\Program Files (x86)\Jenkins\workspace\springboot18\target\Spring_boot_18-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","Spring_boot_18-0.0.1-SNAPSHOT.jar"]
