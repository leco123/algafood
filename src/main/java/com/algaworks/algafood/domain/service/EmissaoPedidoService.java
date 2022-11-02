package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadesNaoEncontrada.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.repository.PedidoRepository;
import com.algaworks.algafood.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmissaoPedidoService {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = cadastroProduto.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    public Pedido buscarOuFalhar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }
}
