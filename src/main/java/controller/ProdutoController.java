package com.vincere.controller;

import com.vincere.model.Produto;
import com.vincere.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepo;

    public ProdutoController(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    // 🔹 LISTAR TODOS
    @GetMapping
    public List<Produto> listar() {
        return produtoRepo.findAll();
    }

    // 🔹 BUSCAR POR ID
    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return produtoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // 🔹 CRIAR PRODUTO (O QUE FALTAVA)
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto salvo = produtoRepo.save(produto);
        return ResponseEntity.ok(salvo);
    }
}
