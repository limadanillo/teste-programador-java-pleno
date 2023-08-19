package com.example.gestao_pedidos.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime issueDate;

    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItemList;

    @NotNull
    @Column(name = "amount", precision = 12, scale = 2)
    private BigDecimal amount;

    public OrderEntity(ClientEntity client, String description, @NotEmpty List<OrderItemEntity> orderItemList,
                       LocalDateTime issueDate) {
        this.client = client;
        this.description = description;
        this.orderItemList = orderItemList;
        this.amount = calculateTotalAmountOrder(orderItemList);
        this.issueDate = issueDate;
    }

    public BigDecimal calculateTotalAmountOrder(List<OrderItemEntity> orderItemList) {
        return Optional.ofNullable(orderItemList).orElseGet(Collections::emptyList)
                .stream()
                .map(OrderItemEntity::getValueItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
