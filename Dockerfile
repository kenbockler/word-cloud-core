#docker build -t word-cloud-core:latest .
#docker tag word-cloud-core estken/word-cloud-core
#docker push estken/word-cloud-core
FROM openjdk:17-alpine
RUN apk add --no-cache curl tar bash
WORKDIR /app
COPY ./build/libs/word-cloud-core.jar /app/word-cloud-core.jar
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true -Xmx256m"
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /app/word-cloud-core.jar
