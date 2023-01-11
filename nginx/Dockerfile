FROM nginx:1.19.6-alpine

# REMOVER O ARQUIVO PADRÃO DO NGINX
RUN rm /etc/nginx/conf.d/default.conf

# COPIAR O ARQUIVO QUE FOI CRIADO nginx.conf e para o caminho abaixo e renomeado o arquivo default.conf
COPY nginx.conf /etc/nginx/conf.d/default.conf