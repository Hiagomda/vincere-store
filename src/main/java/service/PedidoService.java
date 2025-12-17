package com.vincere.service;

import com.vincere.dto.PedidoItemRequest;
import com.vincere.dto.PedidoRequest;
import com.vincere.model.ItemPedido;
import com.vincere.model.Pedido;
import com.vincere.model.VariacaoProduto;
import com.vincere.repository.PedidoRepository;
import com.vincere.repository.VariacaoProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final VariacaoProdutoRepository variacaoRepo;

    public PedidoService(
            PedidoRepository pedidoRepo,
            VariacaoProdutoRepository variacaoRepo
    ) {
        this.pedidoRepo = pedidoRepo;
        this.variacaoRepo = variacaoRepo;
    }

    //  CRIAR PEDIDO
    @Transactional
    public Pedido criarPedido(PedidoRequest request) {

        if (request.getClienteId() == null) {
            throw new IllegalArgumentException("clienteId é obrigatório");
        }

        if (request.getItens() == null || request.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens");
        }

        Pedido pedido = new Pedido();
        pedido.setClienteId(request.getClienteId());

        for (PedidoItemRequest itemReq : request.getItens()) {

            if (itemReq.getVariacaoId() == null) {
                throw new IllegalArgumentException("variacaoId é obrigatório");
            }

            if (itemReq.getQuantidade() == null || itemReq.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida");
            }

            VariacaoProduto variacao = variacaoRepo.findById(itemReq.getVariacaoId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Variação não encontrada: " + itemReq.getVariacaoId()
                            )
                    );

            if (variacao.getEstoque() < itemReq.getQuantidade()) {
                throw new IllegalStateException(
                        "Estoque insuficiente para a variação: " + variacao.getNome()
                );
            }

            // 🔹 baixa estoque
            variacao.setEstoque(
                    variacao.getEstoque() - itemReq.getQuantidade()
            );
            variacaoRepo.save(variacao);

            double precoUnitario =
                    itemReq.getPrecoUnitario() != null
                            ? itemReq.getPrecoUnitario()
                            : (variacao.getPreco() != null
                            ? variacao.getPreco()
                            : variacao.getProduto().getPreco());

            ItemPedido item = new ItemPedido(
                    pedido,
                    variacao,
                    itemReq.getQuantidade(),
                    precoUnitario
            );

            pedido.adicionarItem(item);
        }

        return pedidoRepo.save(pedido);
    }

    //  LISTAR PEDIDOS POR CLIENTE
    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepo.findByClienteId(clienteId);
    }
}
