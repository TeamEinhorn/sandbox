FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY build/libs/sandbox-app.jar /usr/app/

HEALTHCHECK CMD wget -qO- http://localhost:8080/status | grep \"outcome\":\"UP\"

CMD ["java", "-jar", "/usr/app/sandbox-app.jar"]