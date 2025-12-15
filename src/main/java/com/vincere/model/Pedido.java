package com.vincere.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 cliente que fez o pedido
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    // 🔹 status do pedido
    @Column(nullable = false)
    private String status;

    // 🔹 valor total
    @Column(nullable = false)
    private Double total;

    // 🔹 data do pedido
    @Column(nullable = false)
    private LocalDateTime criadoEm;

    // 🔹 itens do pedido
    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemPedido> itens = new ArrayList<>();

    // 🔹 construtor vazio (JPA)
    public Pedido() {
        this.criadoEm = LocalDateTime.now();
        this.status = "CRIADO";
        this.total = 0.0;
    }

    // 🔹 getters e setters
    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    // 🔹 método utilitário
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
        this.total += item.getPrecoUnitario() * item.getQuantidade();
    }
}
