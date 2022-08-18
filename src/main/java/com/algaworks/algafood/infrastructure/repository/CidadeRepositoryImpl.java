package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Repository;

@Repository
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

	@Transactional
	@Override
	public Cidade adicionar(Cidade cidade) {
		try {
			return manager.merge(cidade);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível salvar cidade não pode ser do tipo null");
		}
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cidade cidade = porId(id);

		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cidade);
	}
}
