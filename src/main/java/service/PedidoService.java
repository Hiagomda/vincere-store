package com.vincere.service;

import com.vincere.dto.PedidoItemRequest;
import com.vincere.dto.PedidoRequest;
import com.vincere.model.ItemPedido;
import com.vincere.model.Pedido;
import com.vincere.model.VariacaoProduto;
import com.vincere.repository.PedidoRepository;
import com.vincere.repository.VariacaoProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final VariacaoProdutoRepository variacaoProdutoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            VariacaoProdutoRepository variacaoProdutoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.variacaoProdutoRepository = variacaoProdutoRepository;
    }

    public Pedido criarPedido(PedidoRequest request) {

        if (request.getClienteId() == null) {
            throw new IllegalArgumentException("Cliente não informado");
        }

        if (request.getItens() == null || request.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens");
        }

        // 🔹 cria o pedido
        Pedido pedido = new Pedido();
        pedido.setClienteId(request.getClienteId());

        // 🔹 converte cada item do DTO → Entity
        for (PedidoItemRequest itemReq : request.getItens()) {

            VariacaoProduto variacao = variacaoProdutoRepository
                    .findById(itemReq.getVariacaoId())
                    .orElseThrow(() -> new IllegalArgumentException("Variação não encontrada"));

            if (variacao.getEstoque() < itemReq.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente");
            }

            // 🔹 cria o item do pedido
            ItemPedido item = new ItemPedido(
                    pedido,
                    variacao,
                    itemReq.getQuantidade(),
                    variacao.getPreco()
            );

            // 🔹 adiciona ao pedido
            pedido.adicionarItem(item);

            // 🔹 baixa estoque
            variacao.setEstoque(variacao.getEstoque() - itemReq.getQuantidade());
        }

        return pedidoRepository.save(pedido);
    }
}
