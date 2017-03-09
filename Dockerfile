FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY build/libs/sandbox-fat.jar /usr/verticles/

HEALTHCHECK CMD curl -f http://localhost:8080/status || exit 1 

CMD ["java", "-jar", "/usr/verticles/sandbox-fat.jar"]