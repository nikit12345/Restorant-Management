FROM openjdk:17
EXPOSE 8080
COPY restorent.jar /restorent.jar
ENTRYPOINT ["java", "-jar", "/restorent.jar"]
