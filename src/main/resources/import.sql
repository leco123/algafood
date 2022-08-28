
-- TAB COZINHA
insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Itáliana');

-- TAB RESTAURANTE
insert into restaurante (nome, taxa_frete, cozinha, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
values ('Thai Gourmet', 10, 1, 85504313, "Nome do endereço logradouro 1(um)", "001", "Rua sem nome", "Centro", 1);
insert into restaurante (nome, taxa_frete, cozinha, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
values ('Thai Delivery', 9.50, 2, 85123123, "Nome do endereço logradouro 2(dois)", "002", "Rua sem nome dois", "Centro dois", 1);
insert into restaurante (nome, taxa_frete, cozinha, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
values ('Tuk Tuk Comida Indiana', 15, 1, 85123333, "Nome do endereço logradouro 3(três)", "003", "Rua sem nome três", "Centro três", 1);

-- TAB PERMISÕES DE ACESSO/ESCOPO
insert into permissao (nome, descricao) values ('ADMINISTRADOR','Administrador-Acesso total ao sistema');
insert into permissao (nome, descricao) values ('CLIENTE','Cliente-Acesso aos pedidos e itens');

-- TAB FORMAS DE PAGAMENTO
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

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

-- TAB Relacionamento Forma pagamento restaurante
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1),(1,2),(1,3),(2,3),(3,2),(3,3);


