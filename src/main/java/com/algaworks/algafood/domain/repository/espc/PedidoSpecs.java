package com.algaworks.algafood.domain.repository.espc;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;


public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {

            // if usado para validar se é um page, para evitar erro count,
            // o spring tenta fazer um count no fetch, porém é impossível fazer isso no sql
            // dessa forma deve ser válidado o queryResulType, para saber o resultado da query, se for
            // count não deixar entrar no if
            if (Pedido.class.equals(query.getResultType())) {
                // Resolvendo problema do n+1, que nada mais é que fazer várias consultas na base de dados,
                // mas passando o root.fetch vai retornar o resultado em um único sql, usando api do Criteria
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();
            //adicionar predicates no arraylist
            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }
            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                // data maior ou igual a data passada no parâmetro
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                // data menor ou igual a data passada no parâmetro
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
