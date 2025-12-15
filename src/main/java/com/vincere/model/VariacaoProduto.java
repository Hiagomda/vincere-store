package com.vincere.model;

import jakarta.persistence.*;

@Entity
@Table(name = "variacoes_produto")
public class VariacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Produto pai
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    // Ex: P, M, G | Azul | 42
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer estoque;

    // Pode ser null (usa preço do produto)
    private Double preco;

    // 🔹 construtor vazio (obrigatório)
    public VariacaoProduto() {
    }

    // 🔹 GETTERS E SETTERS (OBRIGATÓRIOS)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
