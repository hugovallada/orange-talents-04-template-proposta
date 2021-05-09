FROM adoptopenjdk:11-jdk
LABEL mantainer = "Hugo Lopes"

WORKDIR /app
COPY ./target/proposta.jar /app

ENTRYPOINT java -jar proposta.jar
EXPOSE 8080