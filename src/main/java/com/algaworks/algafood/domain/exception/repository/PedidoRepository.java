package com.algaworks.algafood.domain.exception.repository;

import com.algaworks.algafood.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Override
    @Query("from Pedido p " +
            "join fetch  p.cliente " +
            "join fetch  p.restaurante r " +
            "join fetch  r.cozinha")
    List<Pedido> findAll();
}
