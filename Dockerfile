FROM openjdk:11

COPY target/jenkins-docker-test.jar app.jar

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "/app.jar"]
