package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    /**
     * EXEMPLO DE COMO IMPLEMENTAR BUSCA/CONSULTA FILTRANDO SOMENTE O PRIMEIRO RESULTADO USANDO PREFIX SPRING JPA DATA
     *
     * find = prefixo que representa a busca, porderia ser trocado por query ou get que também funciona
     * First = prefixo que representa a solicitação de apenas um registro sendo o primeiro registro
     * Restaurante = Nome representativo da busca é opcional
     * By = prefixo
     * Nome = Representa o método da Classe Restaurante que deseja fazer aplicar a consulta
     * Containing = Prefixo confirmando que deve conter tudo que foi definido na pesquisa
     */
    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    /**
     * EXEMPLO DE COMO LIMITAR BUSCANDO OS 2(DOIS) PRIMEIROS REGISTROS
     * TOP = prefixo Top seguido da quantidade de resgitros
     * Ex. limit 2 reg: TOP2
     * * Ex. limit 3 reg: TOP3
     * * Ex. limit 4 reg: TOP4...
     */
    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinhaId);
}
