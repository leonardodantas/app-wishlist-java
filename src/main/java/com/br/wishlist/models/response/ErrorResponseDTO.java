package com.br.wishlist.models.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class ErrorResponseDTO {

    final String uuid;
    private final HttpStatus httpStatus;
    private final LocalDateTime date;
    private final List<DetailErrorResponseDTO> errors;

    private ErrorResponseDTO(ErrorResponseStatusDTO errorResponseStatusDTO, HttpStatus httpStatus) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.errors = Collections.singletonList(DetailErrorResponseDTO.of(errorResponseStatusDTO.getMessage()));
        this.httpStatus = httpStatus;
    }

    private ErrorResponseDTO(List<DetailErrorResponseDTO> errorResponse) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errors = errorResponse;
    }

    private ErrorResponseDTO(HttpStatus status, String message) {
        this.uuid = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.httpStatus = status;
        this.errors = Collections.singletonList(DetailErrorResponseDTO.of(message));
    }

    public static ErrorResponseDTO of(ErrorResponseStatusDTO errorResponseStatus, HttpStatus status) {
        return new ErrorResponseDTO(errorResponseStatus, status);
    }

    public static ErrorResponseDTO of(HttpStatus status, String reason) {
        return new ErrorResponseDTO(status, reason);
    }

    public static ErrorResponseDTO of(List<DetailErrorResponseDTO> detailErrorResponseDTOS) {
        return new ErrorResponseDTO(detailErrorResponseDTOS);
    }

}