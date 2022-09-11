FROM openjdk:11

COPY target/jenkins-docker-test.jar app.jar

EXPOSE 8092

ENTRYPOINT ["java", "-jar", "/app.jar"]
