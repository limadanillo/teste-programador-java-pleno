package com.example.gestao_pedidos.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataBaseErrorException extends Throwable {

    private String errors;

    private static final long serialVersionUID = -769147155483245023L;

    public DataBaseErrorException(String msg) {
        super(msg);
    }

    public DataBaseErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseErrorException(Throwable cause) {
        super(cause);
    }

}
