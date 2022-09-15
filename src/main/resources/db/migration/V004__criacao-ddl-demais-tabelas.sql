create table forma_pagamento (
    id bigint not null auto_increment,
    descricao varchar(60) not null,
    primary key (id)
) engine = InnoDB default charset=utf8;

create table grupo (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    primary key (id)
) engine = InnoDB  default charset=utf8;

create table grupo_permissao (
    grupo_id bigint not null,
    permissao_id bigint not null
) engine = InnoDB  default charset=utf8;

create table permissao (
    id bigint not null auto_increment,
    descricao varchar(60) not null,
    nome varchar(60) not null,
    primary key (id)
) engine = InnoDB default charset=utf8;

create table produto (
    id bigint not null auto_increment,
    descricao varchar(255) not null,
    ativo bit not null,
    nome varchar(255) not null,
    preco decimal(19,2) not null,
    restaurante_id bigint not null,
    primary key (id)
) engine = InnoDB default charset=utf8;

create table restaurante (
    id bigint not null auto_increment,
    data_atualizacao datetime not null,
    data_cadastro datetime not null,
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_complemento varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    nome varchar(150) not null,
    taxa_frete decimal(19,2) not null,
    cozinha_id bigint not null,
    endereco_cidade_id bigint,
    primary key (id)
) engine = InnoDB default charset=utf8;

create table restaurante_forma_pagamento (
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null
) engine = InnoDB default charset=utf8;

create table usuario (
    id bigint not null auto_increment,
    data_cadastro datetime not null,
    email varchar(255) not null,
    nome varchar(255) not null,
    senha varchar(255) not null,
    primary key (id)) engine = InnoDB charset=utf8;

create table usuario_grupo (
    usuario_id bigint not null,
    grupo_id bigint not null
) engine = InnoDB default charset=utf8;

ALTER TABLE grupo_permissao ADD CONSTRAINT FK_grupo_permisao foreign key (permissao_id) REFERENCES permissao (id);
ALTER TABLE grupo_permissao ADD CONSTRAINT FK_grupo_grupo foreign key (grupo_id) REFERENCES grupo (id);
ALTER TABLE produto ADD CONSTRAINT FK_produto_restaurante foreign key (restaurante_id) REFERENCES restaurante (id);
ALTER TABLE restaurante ADD CONSTRAINT FK_restaurante_cozinha foreign key (cozinha_id) REFERENCES cozinha (id);
ALTER TABLE restaurante ADD CONSTRAINT FK_restaurante_endereco_cidade foreign key (endereco_cidade_id) REFERENCES cidade (id);
ALTER TABLE restaurante_forma_pagamento ADD CONSTRAINT FK_restaurante_forma_pagamento foreign key (forma_pagamento_id) REFERENCES forma_pagamento (id);
ALTER TABLE restaurante_forma_pagamento ADD CONSTRAINT FK_restaurante_restaurante foreign key (restaurante_id) REFERENCES restaurante (id);
ALTER TABLE usuario_grupo ADD CONSTRAINT FK_usuario_grupo foreign key (grupo_id) REFERENCES grupo (id);
ALTER TABLE usuario_grupo ADD CONSTRAINT FK_usuario_usuario foreign key (usuario_id) REFERENCES usuario (id);