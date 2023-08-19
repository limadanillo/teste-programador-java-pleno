package com.example.gestao_pedidos.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * Class Error
 */
@Slf4j
@Getter
@NoArgsConstructor
public class Error implements Serializable {
    private int statusCode;
    private ErrorDetails error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ParamErrorDetails> params;
    
    @Builder
    public Error(int statusCode, ErrorDetails error, List<ParamErrorDetails> params) {
        this.statusCode = statusCode;
        this.error = error;
        this.params = params;
    }
}
