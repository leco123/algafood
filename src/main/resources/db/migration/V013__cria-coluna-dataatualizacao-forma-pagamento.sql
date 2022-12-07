-- ADICIONAR COLUNA data_atualizacao NA TABELA forma_pagamento ACEITANDO CAMPOS NULL
alter table forma_pagamento add data_atualizacao datetime NULL;
-- INSERIR REGISTRO UTC_TIMESTAMP
update forma_pagamento set data_atualizacao = UTC_TIMESTAMP;
-- ALTERAR COLUNA PARA NÃO ACEITAR NULL
alter table forma_pagamento modify data_atualizacao datetime not null;