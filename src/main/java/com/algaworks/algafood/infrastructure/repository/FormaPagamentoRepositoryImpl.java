package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamento> todas() {
		return manager.createQuery("from FormaPagamento ", FormaPagamento.class).getResultList();
	}

	@Override
	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}

	@Override
	@Transactional
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		try {
			return manager.merge(formaPagamento);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel salvar forma de pagamento não pode ser do tipo null");
		}
	}

	@Override
	@Transactional
	public void remover(FormaPagamento formaPagamento) {
		try {
			formaPagamento = porId(formaPagamento.getId());
			manager.remove(formaPagamento);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel remover forma de pagamento não pode ser do tipo null");
		}
	}
}
