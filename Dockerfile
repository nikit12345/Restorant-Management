FROM openjdk:17
EXPOSE 8080
ADD target/pipeline-demo.jar pipeline-demo.jar
ENTRYPOINT ["java","-jar","/pipeline-demo.jar"]
   