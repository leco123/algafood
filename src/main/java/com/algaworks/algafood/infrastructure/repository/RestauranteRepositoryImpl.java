package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
	

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> todos() {
		return manager.createQuery("from Restaurante", Restaurante.class)
				.getResultList();
	}
	
	@Override
	public Restaurante porId(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Override
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		try {
			return manager.merge(restaurante);	
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel salvar o restaurante não pode ser do tipo null");
		}
	}
	
	@Override
	@Transactional
	public void remover(Long id) {
		try {
			Restaurante restaurante = porId(id);
			manager.remove(restaurante);	
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel remover a restaurante não pode ser do tipo null");
		}
	}

}
