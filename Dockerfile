FROM adoptopenjdk/openjdk13-openj9:alpine-slim
WORKDIR /tmp

COPY . .
RUN ./gradlew clean build -x test

COPY src ./src
CMD ["./gradlew", "bootRun"]