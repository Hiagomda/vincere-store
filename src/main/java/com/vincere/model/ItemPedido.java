package com.vincere.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 referência ao pedido que contém este item
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    // 🔹 referência à variação comprada (tamanho, cor, etc)
    @ManyToOne(optional = false)
    @JoinColumn(name = "variacao_id", nullable = false)
    private VariacaoProduto variacao;

    // 🔹 quantidade comprada
    @Column(nullable = false)
    private Integer quantidade;

    // 🔹 preço unitário no momento da compra
    @Column(nullable = false)
    private Double precoUnitario;

    // 🔹 construtor vazio (obrigatório para JPA)
    public ItemPedido() {
    }

    // 🔹 construtor útil
    public ItemPedido(
            Pedido pedido,
            VariacaoProduto variacao,
            Integer quantidade,
            Double precoUnitario
    ) {
        this.pedido = pedido;
        this.variacao = variacao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // 🔹 getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public VariacaoProduto getVariacao() {
        return variacao;
    }

    public void setVariacao(VariacaoProduto variacao) {
        this.variacao = variacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
