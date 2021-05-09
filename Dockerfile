## Builder Image
FROM maven:3.6.3-jdk-11 AS builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package


FROM adoptopenjdk:11-jdk
LABEL mantainer = "Hugo Lopes"

COPY --from=builder /usr/src/app/target/proposta.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
