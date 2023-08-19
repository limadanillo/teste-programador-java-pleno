package com.example.gestao_pedidos.core.dtos.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO validar os valores como não obrigatórios
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchRequest {
   private String description;
   private String unit;
   private int page = 0;
   private int size = 10;
}
