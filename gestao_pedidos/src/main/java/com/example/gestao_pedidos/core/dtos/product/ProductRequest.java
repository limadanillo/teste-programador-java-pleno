package com.example.gestao_pedidos.core.dtos.product;

import com.example.gestao_pedidos.core.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link ProductEntity} entity
 */

public record ProductRequest(@NotNull String description, @NotNull String unit,
                             @NotNull BigDecimal price) implements Serializable {
}