package com.example.gestao_pedidos.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ErrorEnum {

    ERROR_BAD_REQUEST("BadRequestError"),
    ERROR_NOT_FOUND("NotFoundError"),
    ERROR_INVALID_PARAM("InvalidParamError"),
    ERROR_INTERNAL_SERVER("InternalServerError");

    private String name;

}
