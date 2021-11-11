package com.br.wishlist.controllers;

import com.br.wishlist.models.request.ProductRequestDTO;
import com.br.wishlist.models.response.ErrorResponseDTO;
import com.br.wishlist.models.response.WishListResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.net.HttpURLConnection;

public interface IAddProductWishListController {

    @PostMapping
    @ApiOperation(value = "Add product to wish list")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success", response = WishListResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDTO request);
}
