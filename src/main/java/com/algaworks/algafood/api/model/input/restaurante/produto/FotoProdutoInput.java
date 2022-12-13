package com.algaworks.algafood.api.model.input.restaurante.produto;

import com.algaworks.algafood.core.validation.annotation.FileContentType;
import com.algaworks.algafood.core.validation.annotation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @ApiModelProperty(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true)
    @NotNull
    @FileSize(max="500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;

    @ApiModelProperty(value = "Descrição da foto do produto", required = true)
    @NotBlank
    private String descricao;
}
