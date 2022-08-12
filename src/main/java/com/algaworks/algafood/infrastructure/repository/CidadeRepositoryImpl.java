package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> todos() {
		return manager.createQuery("from Cidade ", Cidade.class).getResultList();
	}

	@Override
	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Override
	@Transactional
	public Cidade adicionar(Cidade cidade) {
		try {
			return manager.merge(cidade);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível salvar cidade não pode ser do tipo null");
		}
	}

	@Override
	@Transactional
	public void remover(Cidade cidade) {
		try {
			cidade = porId(cidade.getId());
			manager.remove(cidade);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel remover cidade não pode ser do tipo null");
		}
	}
}
