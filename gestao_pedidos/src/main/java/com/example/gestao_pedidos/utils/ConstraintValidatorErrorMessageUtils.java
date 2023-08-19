package com.example.gestao_pedidos.utils;

import jakarta.validation.ConstraintValidatorContext;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Slf4j
@UtilityClass
public class ConstraintValidatorErrorMessageUtils {

    public void setErrorMessage(ConstraintValidatorContext context, MessageSource messageSource, String message, String... placeholders) {
        final HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);
        hibernateContext.disableDefaultConstraintViolation();
        hibernateContext.buildConstraintViolationWithTemplate(messageSource.getMessage(message, placeholders, Locale.getDefault()))
            .addConstraintViolation();
    }

}
