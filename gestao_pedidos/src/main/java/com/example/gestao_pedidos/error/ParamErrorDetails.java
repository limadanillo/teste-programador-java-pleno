package com.example.gestao_pedidos.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Class Param Error Details
 */
@Slf4j
@Getter
@NoArgsConstructor
public class ParamErrorDetails implements Serializable {
    private String message;
    private String property;
    private String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String system;
    
    @Builder
    public ParamErrorDetails(String message, String property, String value, String system) {
        this.message = message;
        this.property = property;
        this.value = value;
        this.system = system;
    }
}
