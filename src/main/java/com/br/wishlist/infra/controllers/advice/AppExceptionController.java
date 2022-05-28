package com.br.wishlist.infra.controllers.advice;

import com.br.wishlist.infra.controllers.advice.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionController {

    private final MessageErrors messageErrors;

    public AppExceptionController(final MessageErrors messageErrors) {
        this.messageErrors = messageErrors;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handlerRuntimeException(final RuntimeException error) {
        final var message = error.getMessage();
        return new ResponseEntity<>(ErrorResponse.of(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException error) {
        final var fieldErrors = error.getBindingResult().getFieldErrors();
        final var listErrors = messageErrors.getListErrors(fieldErrors);
        return new ResponseEntity<>(ErrorResponse.of(listErrors), HttpStatus.BAD_REQUEST);
    }
}