
FROM openjdk:11-jre-slim

ADD   cert/live/www.pyinnyarsubuu.com /www.pyinnyarsubuu.com

VOLUME /tmp

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/mappingSite-0.0.1-SNAPSHOT.jar mappingsite.jar
EXPOSE 8080
# ENTRYPOINT exec java $JAVA_OPTS -jar mappingsite.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar mappingsite.jar
