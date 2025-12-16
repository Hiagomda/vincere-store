
package com.vincere.service;

import com.vincere.dto.PedidoItemRequest;
import com.vincere.dto.PedidoRequest;
import com.vincere.model.ItemPedido;
import com.vincere.model.Pedido;
import com.vincere.model.VariacaoProduto;
import com.vincere.repository.PedidoRepository;
import com.vincere.repository.VariacaoProdutoRepository;
import org.springframework.stereotype.Service; // 👈 ESTE IMPORT

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

    public Pedido criarPedido(PedidoRequest request) {

        if (request.getItens() == null || request.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens");
        }

        Pedido pedido = new Pedido();
        pedido.setClienteId(request.getClienteId());

        for (PedidoItemRequest itemReq : request.getItens()) {

            VariacaoProduto variacao = variacaoRepo.findById(itemReq.getVariacaoId())
                    .orElseThrow(() -> new IllegalArgumentException("Variação não encontrada"));

            // 🔹 verifica estoque
            if (variacao.getEstoque() < itemReq.getQuantidade()) {
                throw new IllegalStateException(
                        "Estoque insuficiente para a variação: " + variacao.getNome()
                );
            }

            // 🔹 baixa estoque
            variacao.setEstoque(
                    variacao.getEstoque() - itemReq.getQuantidade()
            );

            double precoUnitario = itemReq.getPrecoUnitario() != null
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
}
