
-- CRIANDO TABELA DE ESTADO
CREATE table estado (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	
	primary key(id)

) engine=InnoDB default charset=utf8;

-- POPULANDO TABELA DE ESTADO COM NOME DOS ESTADO DA TABELA CIDADE
INSERT estado (nome) SELECT DISTINCT nome_estado from cidade;
-- ADICIONANDO CAMPO ESTADO NA TABELA CIDADE
ALTER table cidade add column estado_id bigint not null;
-- POPULANDO E REFERENCIADO ESTADO NA TABELA DE CIDADE
UPDATE cidade c set c.estado_id = 
(select estado.id from estado where estado.nome = c.nome_estado);
-- Adicionando constraint fk tabela cidade dizendo qual a referência
ALTER table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado (id);
-- Remover da tabela cidade a coluna nome_estado
Alter table cidade drop column nome_estado;
-- Alterar nome cidade nome_cidade
alter table cidade change nome_cidade nome varchar(80) not null;





