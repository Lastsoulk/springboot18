# Maven build container 

FROM maven:3.5.3-jdk-8-alpine AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk:8-jdk-alpine

#expose port 8080
EXPOSE 890

#default command
CMD java -jar /data/hello-world-0.1.0.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/Spring_boot_18-0.1.0.jar /data/Spring_boot_18-0.1.0.jar

