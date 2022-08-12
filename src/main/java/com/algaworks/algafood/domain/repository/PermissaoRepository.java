package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todas();

	Permissao porId(Long id);

	Permissao adicionar(Permissao permissao);

	void remover(Permissao permissao);
}
