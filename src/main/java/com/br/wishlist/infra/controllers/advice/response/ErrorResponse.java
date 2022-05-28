package com.br.wishlist.infra.controllers.advice.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class ErrorResponse {

    private final String uuid;
    private final LocalDateTime date;
    private final List<DetailsErrorsResponse> errors;

    private ErrorResponse(final List<DetailsErrorsResponse> errorResponse) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.errors = errorResponse;
    }

    private ErrorResponse(final String message) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.errors = Collections.singletonList(DetailsErrorsResponse.of(message));
    }

    public static ErrorResponse of(final List<DetailsErrorsResponse> detailsErrorsResponses) {
        return new ErrorResponse(detailsErrorsResponses);
    }

    public static ErrorResponse of(final String message) {
        return new ErrorResponse(message);
    }
}