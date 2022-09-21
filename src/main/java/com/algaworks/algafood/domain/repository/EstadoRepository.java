package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

    List<Estado> findTodasByNomeContaining(String nome);

    Optional<Estado> findByNome(String nome);

    boolean existsByNome(String nome);
}
