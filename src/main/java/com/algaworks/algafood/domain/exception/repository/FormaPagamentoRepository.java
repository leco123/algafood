package com.algaworks.algafood.domain.exception.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
