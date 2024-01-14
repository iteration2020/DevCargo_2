FROM openjdk:21-jdk
CMD sleep 100
ADD build/libs/DevCargo-0.0.1-SNAPSHOT.jar DevCargo.jar
COPY build/resources/ /resources
ENTRYPOINT ["java", "-jar","DevCargo.jar","--spring.config.location=file:/resources/main/application.properties"]