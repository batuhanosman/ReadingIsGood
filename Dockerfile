FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} readingisgood-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/readingisgood-0.0.1-SNAPSHOT.jar"]