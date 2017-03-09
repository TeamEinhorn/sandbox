FROM openjdk:8-jre-alpine

ENV VERTX_FILE sandbox-fat.jar

ENV VERTX_HOME /usr/verticles

EXPOSE 8080

COPY build/libs/$VERTX_FILE $VERTX_HOME/

HEALTHCHECK CMD curl -f http://localhost:8080/status || exit 1 

CMD ["java", "-jar", "$VERTX_HOME/$VERTX_FILE"]