package com.example.Smash.dto;

import java.util.List;
import java.util.UUID;

public class PedidoRequestDTO {

    private UUID clienteId;
    private List<Long> comidasIds;

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getComidasIds() {
        return comidasIds;
    }

    public void setComidasIds(List<Long> comidasIds) {
        this.comidasIds = comidasIds;
    }
}
