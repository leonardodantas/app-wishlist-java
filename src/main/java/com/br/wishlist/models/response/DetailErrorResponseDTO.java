package com.br.wishlist.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailErrorResponseDTO {

    private String field;
    private String message;

    private DetailErrorResponseDTO(String message) {
        this.message = message;
    }

    private DetailErrorResponseDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static DetailErrorResponseDTO of(String field, String message) {
        return new DetailErrorResponseDTO(field, message);
    }

    public static DetailErrorResponseDTO of(String message) {
        return new DetailErrorResponseDTO(message);
    }
}
