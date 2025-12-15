package com.vincere.controller;

import com.vincere.dto.PedidoRequest;
import com.vincere.dto.PedidoResponse;
import com.vincere.model.Pedido;
import com.vincere.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@RequestBody PedidoRequest req) {
        try {
            Pedido pedido = pedidoService.criarPedido(req);
            return ResponseEntity.ok(new PedidoResponse(pedido.getId(), pedido.getStatus(), pedido.getTotal()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new PedidoResponse(null, "ERROR: " + e.getMessage(), 0.0));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(new PedidoResponse(null, "ESTOQUE_INSUFICIENTE", 0.0));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new PedidoResponse(null, "ERRO_INTERNO", 0.0));
        }
    }
}
