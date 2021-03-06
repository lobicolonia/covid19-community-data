FROM openjdk:8-jdk-alpine
VOLUME /tmp

COPY build/libs/*.jar app.jar

COPY ./communitydata.xlsx communitydata.xlsx

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]