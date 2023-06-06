FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace
COPY .mvn .mvn
COPY src src
COPY mvnw mvnw
COPY pom.xml pom.xml
RUN ./mvnw install

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build workspace/target/senior-java-test.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]