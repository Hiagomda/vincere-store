package com.vincere.dto;

public class PedidoResponse {
    private Long id;
    private String status;
    private Double total;

    public PedidoResponse() {}
    public PedidoResponse(Long id, String status, Double total) {
        this.id = id; this.status = status; this.total = total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}
