package com.example.gestao_pedidos.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Class Error Details
 */
@Slf4j
@Getter
@NoArgsConstructor
public class ErrorDetails implements Serializable {
    private String name;
    private String message;
    private Long time;
    
    @Builder
    public ErrorDetails(String name, String message, Long time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }
}
