FROM openjdk:8-jre-alpine

ARG APP_VERSION

LABEL version=$APP_VERSION
LABEL maintainer=TeamEinhorn
LABEL source=https://github.com/teameinhorn/sandbox/

EXPOSE 8080

COPY build/libs/sandbox-$APP_VERSION-app.jar /usr/app/

HEALTHCHECK CMD wget -qO- http://localhost:8080/status | grep \"outcome\":\"UP\"

CMD java -jar /usr/app/sandbox-${APP_VERSION}-app.jar