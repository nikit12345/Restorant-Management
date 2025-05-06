FROM eclipse-temurin:17-jdk-jammy
WORKDIR	/opt
ENV PORT 8082
EXPOSE 8082
COPY target/*jar /opt/restorent-management.jar
ENTRYPOINT exec java $JAVA_OPTS -jar restorent-management.jar
   
   
  
