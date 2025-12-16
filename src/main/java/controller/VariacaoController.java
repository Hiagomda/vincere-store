package com.vincere.controller;

import com.vincere.dto.VariacaoProdutoRequest;
import com.vincere.model.Produto;
import com.vincere.model.VariacaoProduto;
import com.vincere.repository.ProdutoRepository;
import com.vincere.repository.VariacaoProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variacoes")
public class VariacaoController {

    private final VariacaoProdutoRepository variacaoRepo;
    private final ProdutoRepository produtoRepo;

    public VariacaoController(
            VariacaoProdutoRepository variacaoRepo,
            ProdutoRepository produtoRepo
    ) {
        this.variacaoRepo = variacaoRepo;
        this.produtoRepo = produtoRepo;
    }

    // LISTAR
    @GetMapping
    public List<VariacaoProduto> listar() {
        return variacaoRepo.findAll();
    }

    //CRIAR VARIAÇÃO
    @PostMapping
    public VariacaoProduto criar(@RequestBody VariacaoProdutoRequest req) {

        Produto produto = produtoRepo.findById(req.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        VariacaoProduto variacao = new VariacaoProduto();
        variacao.setProduto(produto);
        variacao.setNome(req.getNome());
        variacao.setEstoque(req.getEstoque());
        variacao.setPreco(req.getPreco());

        return variacaoRepo.save(variacao);
    }
}
