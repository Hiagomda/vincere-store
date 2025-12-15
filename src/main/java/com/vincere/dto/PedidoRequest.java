package com.vincere.dto;

import java.util.List;

public class PedidoRequest {
    private Long clienteId;
    private List<PedidoItemRequest> itens;
    private Double total; // opcional: server pode recalcular

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<PedidoItemRequest> getItens() { return itens; }
    public void setItens(List<PedidoItemRequest> itens) { this.itens = itens; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}
