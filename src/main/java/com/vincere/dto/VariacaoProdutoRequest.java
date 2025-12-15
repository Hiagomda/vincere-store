package com.vincere.dto;

public class VariacaoProdutoRequest {

    private Long produtoId;
    private String nome;       // ex: P, M, G | Azul | 42
    private Integer estoque;
    private Double preco;      // opcional: se variar do produto

    // 🔹 getters
    public Long getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public Double getPreco() {
        return preco;
    }

    // 🔹 setters (SEM return!)
    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
