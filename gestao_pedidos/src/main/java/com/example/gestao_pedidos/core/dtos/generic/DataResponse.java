package com.example.gestao_pedidos.core.dtos.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class Data Transfer Object Data Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataResponse {
    private List<? extends Object> list;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageInfoResponse pageInfo;
}