package com.vincere.dto;

public class PedidoItemRequest {
    private Long variacaoId;
    private Integer quantidade;
    private Double precoUnitario; // opcional: podemos calcular pelo produto

    public Long getVariacaoId() { return variacaoId; }
    public void setVariacaoId(Long variacaoId) { this.variacaoId = variacaoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(Double precoUnitario) { this.precoUnitario = precoUnitario; }
}
