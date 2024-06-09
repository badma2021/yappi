FROM openjdk:17-alpine
LABEL "name"="yappi"
ARG JAR_FILE=target/yappi-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
