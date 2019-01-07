FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD doodle_demo-0.0.1-SNAPSHOT.jar doodle_demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/doodle_demo-0.0.1-SNAPSHOT.jar"]

