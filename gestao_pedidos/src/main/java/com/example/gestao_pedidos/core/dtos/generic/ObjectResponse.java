package com.example.gestao_pedidos.core.dtos.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Class Data Transfer Object List generic Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectResponse {
    private Integer statusCode;
    private Object data;
}
