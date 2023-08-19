package com.example.gestao_pedidos.core.dtos.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Data Transfer Object Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfoResponse {
    private Integer currentPage;
    private Integer pageCount;
    private Integer pageSize;
    private Integer count;
}