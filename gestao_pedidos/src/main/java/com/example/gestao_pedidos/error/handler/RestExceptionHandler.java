package com.example.gestao_pedidos.error.handler;

import com.example.gestao_pedidos.error.Error;
import com.example.gestao_pedidos.error.ErrorDetails;
import com.example.gestao_pedidos.error.ParamErrorDetails;
import com.example.gestao_pedidos.error.ValidationError;
import com.example.gestao_pedidos.error.exception.BadRequestErrorException;
import com.example.gestao_pedidos.error.exception.DataBaseErrorException;
import com.example.gestao_pedidos.error.exception.InternalServerErrorException;
import com.example.gestao_pedidos.error.exception.NotFoundErrorException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.gestao_pedidos.core.enums.ErrorEnum.ERROR_BAD_REQUEST;
import static com.example.gestao_pedidos.core.enums.ErrorEnum.ERROR_INTERNAL_SERVER;
import static com.example.gestao_pedidos.core.enums.ErrorEnum.ERROR_INVALID_PARAM;
import static com.example.gestao_pedidos.core.enums.ErrorEnum.ERROR_NOT_FOUND;

/**
 * Class Rest Exception Handler
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(ChangeSetPersister.NotFoundException ex) {
        Error error = Error.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error(ErrorDetails.builder()
                        .name(ERROR_NOT_FOUND.getName())
                        .message(ex.getMessage())
                        .time(new Date().getTime())
                        .build())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> handlerInternalServerErrorException(InternalServerErrorException ex) {
        String msg =  messageSource.getMessage("message.internal.error", null, Locale.getDefault());
        Error error = Error.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ErrorDetails.builder()
                        .name(ERROR_INTERNAL_SERVER.getName())
                        .message(msg)
                        .time(new Date().getTime())
                        .build())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException ex) {
        List<ParamErrorDetails> params = ValidationError.getParamErrorDetails(ex);
        String msg =  messageSource.getMessage("message.error.param", null, Locale.getDefault());
        Error error = Error.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error(ErrorDetails.builder()
                        .name(ERROR_INVALID_PARAM.getName())
                        .message(msg)
                        .time(new Date().getTime())
                        .build())
                .params(params)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundErrorException.class)
    public ResponseEntity<?> handlerNotFoundServerErrorException(NotFoundErrorException ex) {
        Error error = Error.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error(ErrorDetails.builder()
                        .name(ERROR_NOT_FOUND.getName())
                        .message(ex.getMessage())
                        .time(new Date().getTime())
                        .build())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestErrorException.class)
    public ResponseEntity<?> handlerBadRequestErrorException(BadRequestErrorException ex) {
        Error error = Error.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error(ErrorDetails.builder()
                        .name(ERROR_BAD_REQUEST.getName())
                        .message(ex.getMessage())
                        .time(new Date().getTime())
                        .build())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WebExchangeBindException.class})
    public ResponseEntity<Object> handleJacksonError(WebExchangeBindException ex) {
        List<ParamErrorDetails> params = ValidationError.getParamErrorDetails(ex);
        final String msg = (Objects.isNull(ex.getBindingResult()) || ex.getBindingResult().getAllErrors().isEmpty())
            ? messageSource.getMessage("message.error.param", null, Locale.getDefault())
            : ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .distinct()
                .collect(Collectors.joining(", "));
        final Error error = Error.builder()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .error(ErrorDetails.builder()
                .name(ERROR_INVALID_PARAM.getName())
                .message(msg)
                .time(new Date().getTime())
                .build())
            .params(params)
            .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {DataBaseErrorException.class, DataAccessResourceFailureException.class})
    public ResponseEntity<?> handlerDataBaseErrorException(DataBaseErrorException ex) {
        String msg =  messageSource.getMessage("message.internal.error", null, Locale.getDefault());
        Error error = Error.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .error(ErrorDetails.builder()
                        .name(ERROR_NOT_FOUND.getName())
                        .message(ex.getMessage())
                        .time(new Date().getTime())
                        .build())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}