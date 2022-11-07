package com.algaworks.algafood.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    /**
     * Método que pode ser chamado para solicitar a implementação
     * para serializar valores do tipo que esse serializador manipula.
     *
     * @param page       Valor a ser serializado; não pode ser nulo.
     * @param gen         Gerador usado para gerar o conteúdo Json resultante
     * @param serializers O provedor que pode ser usado para obter serializadores para
     *                    serializar o valor de objetos contém, se houver.
     */
    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();  // Iníciar objeto

        // escrever pra mim uma propriedade de objeto, o nome dessa propriedade
        // é content eo conteudo dela é page.getContent()
        gen.writeObjectField("content", page.getContent());

        // CONFIGURAR AS PROPRIEDADES DA PÁGINA

        // tamanho da página, quantos elementos deve mostrar por página
        gen.writeNumberField("size", page.getSize());

        // total de elementos encontrado na base de dados
        gen.writeNumberField("totalElements", page.getTotalElements());

        // total de páginas
        gen.writeNumberField("totalPages", page.getTotalPages());

        // qual pagina que esta acessando atualmente
        gen.writeNumberField("number", page.getNumber());

       gen.writeEndObject();  // Finalizar objeto
    }
}
