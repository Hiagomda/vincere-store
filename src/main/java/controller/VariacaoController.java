package com.vincere.controller;

import com.vincere.dto.VariacaoProdutoRequest;
import com.vincere.model.VariacaoProduto;
import com.vincere.service.VariacaoProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variacoes")
public class VariacaoController {

    private final VariacaoProdutoService variacaoService;

    public VariacaoController(VariacaoProdutoService variacaoService) {
        this.variacaoService = variacaoService;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody VariacaoProdutoRequest req) {
        try {
            VariacaoProduto v = variacaoService.criar(req);
            return ResponseEntity.ok(v);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
