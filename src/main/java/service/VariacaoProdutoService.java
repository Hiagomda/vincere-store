package com.vincere.service;

import com.vincere.dto.VariacaoProdutoRequest;
import com.vincere.model.Produto;
import com.vincere.model.VariacaoProduto;
import com.vincere.repository.ProdutoRepository;
import com.vincere.repository.VariacaoProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class VariacaoProdutoService {

    private final ProdutoRepository produtoRepository;
    private final VariacaoProdutoRepository variacaoRepository;

    public VariacaoProdutoService(
            ProdutoRepository produtoRepository,
            VariacaoProdutoRepository variacaoRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.variacaoRepository = variacaoRepository;
    }

    public VariacaoProduto criar(VariacaoProdutoRequest request) {

        //  Buscar produto
        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        //  Criar variação
        VariacaoProduto variacao = new VariacaoProduto();
        variacao.setProduto(produto);
        variacao.setNome(request.getNome());
        variacao.setEstoque(request.getEstoque());
        variacao.setPreco(request.getPreco());
        variacao.setDescricao(request.getDescricao());

        //  Salvar
        return variacaoRepository.save(variacao);
    }
}
