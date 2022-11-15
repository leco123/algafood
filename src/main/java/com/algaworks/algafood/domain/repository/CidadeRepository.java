package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CidadeRepository  extends CustomJpaRepository<Cidade, Long> {
    List<Cidade> findTodasByNomeContaining(String nome);

    Optional<Cidade> findByNome(String nome);

    boolean existsByNome(String nome);
}
