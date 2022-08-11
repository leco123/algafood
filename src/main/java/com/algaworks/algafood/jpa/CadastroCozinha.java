package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha", Cozinha.class)
				.getResultList();
	}
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		try {
			return manager.merge(cozinha);	
		} catch (Exception e) {
			throw new RuntimeException("Cozinha não pode ser do tipo null");
		}
	}
	
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
}