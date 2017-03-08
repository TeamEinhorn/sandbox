FROM openjdk:8-jre-alpine

ENV VERTX_FILE sandbox-fat.jar

ENV VERTX_HOME /usr/verticles

EXPOSE 8080

COPY build/libs/$VERTX_FILE $VERTX_HOME/

WORKDIR $VERTX_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $VERTICLE_FILE"]