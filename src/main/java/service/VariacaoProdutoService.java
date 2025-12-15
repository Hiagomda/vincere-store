package com.vincere.service;

import com.vincere.dto.VariacaoProdutoRequest;
import com.vincere.model.Produto;
import com.vincere.model.VariacaoProduto;
import com.vincere.repository.ProdutoRepository;
import com.vincere.repository.VariacaoProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class VariacaoProdutoService {

    private final ProdutoRepository produtoRepo;
    private final VariacaoProdutoRepository variacaoRepo;

    public VariacaoProdutoService(
            ProdutoRepository produtoRepo,
            VariacaoProdutoRepository variacaoRepo
    ) {
        this.produtoRepo = produtoRepo;
        this.variacaoRepo = variacaoRepo;
    }

    public VariacaoProduto criar(VariacaoProdutoRequest req) {

        // 1️⃣ buscar produto
        Produto produto = produtoRepo.findById(req.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        // 2️⃣ criar variação
        VariacaoProduto variacao = new VariacaoProduto();
        variacao.setProduto(produto);
        variacao.setNome(req.getNome());
        variacao.setEstoque(req.getEstoque());
        variacao.setPreco(req.getPreco());

        // 3️⃣ salvar
        return variacaoRepo.save(variacao);
    }
}
