FROM alpine:edge

MAINTAINER car.com

RUN apk add --no-cache openjdk8

EXPOSE 8020 8120

VOLUME /logs

WORKDIR /

COPY /build/libs/cars-1.0.jar /car.jar

ENTRYPOINT exec java -jar /car.jar