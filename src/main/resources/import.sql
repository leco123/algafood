
-- TAB COZINHA
insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');

-- TAB RESTAURANTE
insert into restaurante (nome, taxa_frete, cozinha) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha) values ('Thai Delivery', 9.50, 2);
insert into restaurante (nome, taxa_frete, cozinha) values ('Tuk Tuk Comida Indiana', 15, 1);

-- TAB PERMISÕES DE ACESSO/ESCOPO
insert into permissao (nome, descricao) values ('ADMINISTRADOR','Administrador-Acesso total ao sistema');
insert into permissao (nome, descricao) values ('CLIENTE','Cliente-Acesso aos pedidos e itens');

-- TAB FORMAS DE PAGAMENTO
insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão');

-- TAB Estados do país
insert into estado (nome) values ('Paraná');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Santa Catarina');
insert into estado (nome) values ('Rio Grande do Sul');

-- TAB cidade
insert into cidade (nome, estado) values ('Pato Branco', 1);
insert into cidade (nome, estado) values ('Clevelândia', 1);
insert into cidade (nome, estado) values ('Gramado',4);
insert into cidade (nome, estado) values ('Canela',4);


