# Use uma imagem base com o MySQL 8 instalado
FROM mysql:8.0

# Copie o arquivo de configuração para o contêiner
COPY mysql/mysql.cnf /etc/mysql/conf.d/mysql.cnf

# Defina as variáveis de ambiente para as credenciais de acesso ao banco de dados
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes

# Exponha a porta padrão do MySQL (3306)
EXPOSE 3306

FROM nginx:1.19
COPY nginx/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80

FROM openjdk:12

## WORKDIR ONDE AS instruções vão rodar
WORKDIR /app

ARG JAR_FILE

## COPY copiando aplicação para dentro do app e nomeando com api.jar
COPY target/${JAR_FILE} /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

## QUAL PORTA O CONTAINER VAI ESCUTAR
EXPOSE 8080

## COMANDO PADRÃO QUE O DOCKER CONTAINER LEVANTAR
CMD ["java", "-jar", "api.jar"]