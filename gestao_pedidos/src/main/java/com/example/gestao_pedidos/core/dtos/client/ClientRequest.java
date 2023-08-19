package com.example.gestao_pedidos.core.dtos.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.gestao_pedidos.core.entity.ClientEntity} entity
 */
public record ClientRequest(String name, String cpf, String telephone, String email) implements Serializable {
}