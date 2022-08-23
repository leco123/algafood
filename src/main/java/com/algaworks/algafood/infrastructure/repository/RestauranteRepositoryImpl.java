package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        // root representa a raiz do restaurante
        Root<Restaurante> root =  criteria.from(Restaurante.class);

        //Predicado
        Predicate nomePredicate = builder.like(root.get("nome"), "%"+nome+"%");
        Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
        Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);

        criteria.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
    }
}
