package com.example.gestao_pedidos.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private int amount;
    private BigDecimal valueItem;

    public OrderItemEntity(@NonNull ProductEntity product, int amount) {
        this.product = product;
        this.amount = amount;
        this.valueItem = getItemValue(product.getPrice());
    }

    private BigDecimal getItemValue(BigDecimal productPrice) {
        return productPrice.multiply(new BigDecimal(this.amount));
    }
}
