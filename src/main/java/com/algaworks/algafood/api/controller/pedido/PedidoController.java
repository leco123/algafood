package com.algaworks.algafood.api.controller.pedido;

import com.algaworks.algafood.api.assembler.pedido.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.pedido.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.pedido.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.input.pedido.PedidoInput;
import com.algaworks.algafood.api.model.pedido.PedidoModel;
import com.algaworks.algafood.api.model.pedido.PedidoResumoModel;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.repository.PedidoRepository;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    /*
     13.2. Limitando os campos retornados pela API com @JsonFilter do Jackson
    @GetMapping
    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos);

        // Instanciado o Envelope
        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);

        // tipo de filtro que será feito
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        // Serealiza todas as propriedades do PedidoResumoModel usando método SimpleBeanPropertyFilter.serializeAll()
        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
        // valida se existe campos e passa os valores campos exemplo: campos=codigo,valorToral
        if (StringUtils.isNotEmpty(campos)) {
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }

        pedidosWrapper.setFilters(filterProvider);

        return pedidosWrapper;
    }
*/

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();
        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}