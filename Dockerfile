
   
   
FROM openjdk:17
EXPOSE 8080
COPY restorent.jar /restorent.jar  # Use COPY instead of ADD
ENTRYPOINT ["java", "-jar", "/restorent.jar"]
 