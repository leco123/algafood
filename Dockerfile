FROM openjdk:12-alpine

## WORKDIR ONDE AS instruções vão rodar
WORKDIR /app

ARG JAR_FILE

## COPY copiando aplicação para dentro do app e nomeando com api.jar
COPY target/${JAR_FILE} /app/api.jar

## QUAL PORTA O CONTAINER VAI ESCUTAR
EXPOSE 8080

## COMANDO PADRÃO QUE O DOCKER CONTAINER LEVANTAR
CMD ["java", "-jar", "api.jar"]