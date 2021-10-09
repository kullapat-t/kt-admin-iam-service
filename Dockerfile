FROM adoptopenjdk/openjdk13-openj9:alpine-slim
WORKDIR /output

COPY . .
RUN ./gradlew clean build -x test
COPY /build/libs/*.jar /service.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /service.jar" ]