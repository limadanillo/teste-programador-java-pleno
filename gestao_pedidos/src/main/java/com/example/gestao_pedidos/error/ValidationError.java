package com.example.gestao_pedidos.error;

import com.example.gestao_pedidos.utils.StaticContextUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Class Validation Error
 */
@Slf4j
public class ValidationError {

    public static final String MSG = "msg";
    public static final String VALUE = "value";
    public static final String TYPE = "type";
    public static final String FROM_TO_ALREADY_REGISTERED = "DE-PARA já cadastrado.";
    public static final String MNEMONIC_DASA_NOT_ENABLED_IN_UNIT = "Mnemônico DASA não habilitado na unidade";
    public static final String FROM_TO_NOW_REGISTERED_WITH_NEW_CODE = "DE-PARA já cadastrado com o novo código.";
    public static final String FROM_TO_NOT_FOUND = "DE-PARA não encontrado.";

    public static List<ParamErrorDetails> getParamErrorDetails(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violation = ex.getConstraintViolations();
        List<String> field = new ArrayList<>();
        List<ParamErrorDetails> params = new ArrayList<>();
        violation.forEach(x ->{
            Iterator<Path.Node> nodes = x.getPropertyPath().iterator();
            Path.Node firstNode = nodes.next();
            Path.Node secondNode = nodes.next();
            params.add(ParamErrorDetails.builder()
                    .value(x.getInvalidValue().toString())
                    .message(x.getMessage())
                    .property(secondNode.getName())
                    .build());
        });
        return params;
    }

    public static List<ParamErrorDetails> getParamErrorDetails(WebExchangeBindException ex) {
        List<FieldError> allErrors = ex.getFieldErrors();
        List<ParamErrorDetails> params = new ArrayList<>();
        allErrors.forEach(x ->{
            params.add(ParamErrorDetails.builder()
                    .value(getValue(x))
                    .message(messageSource(x))
                    .property(x.getField())
                    .build());
        });
        return params;
    }
    private static String getValue(FieldError x) {
        return x.getRejectedValue() != null ? x.getRejectedValue().toString() : null;
    }

    private static String messageSource(FieldError x) {
        String code = new StringBuffer("message.error.").
                append(x.getField().replaceAll("\\[(.*?)\\]", "").toLowerCase())
                .append(".")
                .append(x.getCode().toLowerCase())
                .toString();
        String msg = "";
        MessageSource messageSource = StaticContextUtils.getBean(MessageSource.class);
        try {
            msg = messageSource.getMessage(code, null, Locale.getDefault());
        } catch (NoSuchMessageException e){
            msg = getMessageDefaultOrCustomized(x, messageSource);
        }
        return msg;
    }

    private static String getMessageDefaultOrCustomized(FieldError x, MessageSource messageSource) {
        String msg;
        if(x.getDefaultMessage().contains("{") && x.getDefaultMessage().contains("}")) {
            msg = messageSource.getMessage(x.getDefaultMessage().replaceAll("\\{", "").replaceAll("\\}", ""), null, Locale.getDefault());
        } else {
            msg = x.getDefaultMessage();
        }
        return msg;
    }

    public static String getMessageDPIntegratorCustomized(String messageApiDP) {
        MessageSource messageSource = StaticContextUtils.getBean(MessageSource.class);
        return switch (messageApiDP) {
            case FROM_TO_ALREADY_REGISTERED -> messageSource.getMessage("message.error.fromto.not.able", null, Locale.getDefault());
            case MNEMONIC_DASA_NOT_ENABLED_IN_UNIT -> messageSource.getMessage("message.error.fromto.already.registered", null, Locale.getDefault());
            case FROM_TO_NOW_REGISTERED_WITH_NEW_CODE -> messageSource.getMessage("message.error.fromto.already.registered.new.code", null, Locale.getDefault());
            case FROM_TO_NOT_FOUND -> messageSource.getMessage("message.error.fromto.not.found", null, Locale.getDefault());
            default -> messageApiDP;
        };
    }
}
