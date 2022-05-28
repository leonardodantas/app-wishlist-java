package com.br.wishlist.infra.controllers.advice;

import com.br.wishlist.infra.controllers.advice.response.DetailsErrorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageErrors {

    @Autowired
    private MessageSource messageSource;

    public List<DetailsErrorsResponse> getListErrors(final List<FieldError> fieldErrors){
        return fieldErrors.stream()
                .map(this::getError)
                .collect(Collectors.toUnmodifiableList());
    }

    private DetailsErrorsResponse getError(final FieldError field){
        final var message = messageSource.getMessage(field, LocaleContextHolder.getLocale());
        return DetailsErrorsResponse.of(field.getField(), message);
    }
}
