package com.vincere.model;

import jakarta.persistence.*;

@Entity
@Table(name = "variacoes")
public class VariacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // várias variações pertencem a um produto
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // exemplo: "P", "M", "G", "38", "40", "UNICO"
    private String tamanho;

    // estoque disponível
    private Integer estoque = 0;

    // código interno opcional
    private String sku;

    // Construtor vazio (necessário para JPA)
    public VariacaoProduto() {}

    // Construtor útil para testes
    public VariacaoProduto(Produto produto, String tamanho, Integer estoque, String sku) {
        this.produto = produto;
        this.tamanho = tamanho;
        this.estoque = estoque;
        this.sku = sku;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
}
