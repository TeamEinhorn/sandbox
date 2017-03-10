FROM openjdk:8-jre-alpine

ARG app_version

LABEL version=$app_version
LABEL maintainer=TeamEinhorn
LABEL source=https://github.com/teameinhorn/sandbox/

EXPOSE 8080

COPY build/libs/sandbox-$app_version-app.jar /usr/app/

HEALTHCHECK CMD wget -qO- http://localhost:8080/status | grep \"outcome\":\"UP\"

CMD ["java", "-jar", "/usr/app/sandbox-$app_version-app.jar"]