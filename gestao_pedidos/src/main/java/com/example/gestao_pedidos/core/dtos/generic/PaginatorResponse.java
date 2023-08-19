package com.example.gestao_pedidos.core.dtos.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Class Data Transfer Object List Paginator Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatorResponse {
    private Integer statusCode;
    private DataResponse data;
}
