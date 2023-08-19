package com.example.gestao_pedidos.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class Bad Request Error Exception
 */
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestErrorException extends Throwable {

    private static final long serialVersionUID = -769147155483245023L;

    public BadRequestErrorException(String msg) {
        super(msg);
    }

    public BadRequestErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestErrorException(Throwable cause) {
        super(cause);
    }

}