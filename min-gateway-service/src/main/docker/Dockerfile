FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
EXPOSE 8080
ADD min-gateway-service-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]