package com.algaworks.algafood.domain.model.dtos.input;

import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.annotation.TaxaFrete;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class RestauranteInput {

    @NotBlank
    @Column(length = 150, nullable = false)
    private String nome;

    @NotNull
    @TaxaFrete
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInput cozinha;
}
