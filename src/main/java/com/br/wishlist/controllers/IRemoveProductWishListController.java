package com.br.wishlist.controllers;

import com.br.wishlist.models.response.ErrorResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpURLConnection;

public interface IRemoveProductWishListController {

    @DeleteMapping
    @ApiOperation(value = "Remove product from wish list")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success"),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> removeProduct(@RequestParam String customerId, @RequestParam String productId);
}
