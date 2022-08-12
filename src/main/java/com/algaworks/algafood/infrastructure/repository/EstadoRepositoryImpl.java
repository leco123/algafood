package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> todos() {
		return manager.createQuery("from Estado ", Estado.class).getResultList();
	}

	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado adicionar(Estado estado) {
		try {
			return manager.merge(estado);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível salvar estado/província não pode ser do tipo null");
		}
	}

	@Override
	@Transactional
	public void remover(Estado estado) {
		try {
			estado = porId(estado.getId());
			manager.remove(estado);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel remover estado/província não pode ser do tipo null");
		}
	}
}
