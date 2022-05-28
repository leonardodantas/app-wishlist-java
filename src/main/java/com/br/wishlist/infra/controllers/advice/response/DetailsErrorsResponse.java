package com.br.wishlist.infra.controllers.advice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailsErrorsResponse {

    private String field;
    private String message;

    private DetailsErrorsResponse(final String message) {
        this.message = message;
    }

    private DetailsErrorsResponse(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    public static DetailsErrorsResponse of(final String field, final String message) {
        return new DetailsErrorsResponse(field, message);
    }

    public static DetailsErrorsResponse of(final String message) {
        return new DetailsErrorsResponse(message);
    }
}
