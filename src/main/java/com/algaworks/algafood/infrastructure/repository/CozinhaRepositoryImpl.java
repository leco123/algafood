package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> todas() {
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	}
	
	@Override
	public Cozinha porId(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Override
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		try {
			return manager.merge(cozinha);	
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel salvar a cozinha não pode ser do tipo null");
		}
	}
	
	@Override
	@Transactional
	public void remover(Cozinha cozinha) {
		try {
			cozinha = porId(cozinha.getId());
			manager.remove(cozinha);	
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel remover a cozinha não pode ser do tipo null");
		}
	}
}
