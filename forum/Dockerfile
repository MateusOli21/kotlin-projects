FROM adoptopenjdk/openjdk8

EXPOSE 8080

#ADD /target/forum-0.0.1-SNAPSHOT.jar app.jar

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]