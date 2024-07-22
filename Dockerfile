FROM openjdk:17
EXPOSE 8080
ADD target/restorent.jar restorent.jar
ENTRYPOINT ["java","-jar","/restorent.jar"]
   