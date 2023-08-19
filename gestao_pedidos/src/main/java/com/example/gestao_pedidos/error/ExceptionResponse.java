package com.example.gestao_pedidos.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExceptionResponse extends Exception {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;

    private String[] errors;

    public ExceptionResponse(String message) {
        super(message);
    }

    /**
     * Status has to be converted into {@link HttpStatus}
     */
    public void setStatus(String status) {
        this.status = HttpStatus.valueOf(Integer.valueOf(status));
    }

}