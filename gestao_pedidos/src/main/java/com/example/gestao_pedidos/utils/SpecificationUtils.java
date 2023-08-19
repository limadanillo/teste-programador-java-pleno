package com.example.gestao_pedidos.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SpecificationUtils {
    public String like(String value) {
       return "%" + value + "%";
    }
}
