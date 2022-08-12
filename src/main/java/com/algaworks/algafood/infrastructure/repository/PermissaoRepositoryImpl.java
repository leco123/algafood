package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> todas() {
		return manager.createQuery("from Permisao", Permissao.class).getResultList();
	}

	@Override
	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao adicionar(Permissao permissao) {
		try {
			return manager.merge(permissao);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel salvar a permisão não pode ser do tipo null");
		}
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		try {
			permissao = porId(permissao.getId());
			manager.remove(permissao);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel remover a permisão não pode ser do tipo null");
		}
	}
}
