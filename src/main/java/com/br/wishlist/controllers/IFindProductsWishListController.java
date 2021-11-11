package com.br.wishlist.controllers;

import com.br.wishlist.models.response.ErrorResponseDTO;
import com.br.wishlist.models.response.ProductResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.HttpURLConnection;

public interface IFindProductsWishListController {

    @GetMapping("/{customerId}/products")
    @ApiOperation(value = "Search all user products")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponseDTO.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "No content"),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> findAllProducts(@PathVariable String customerId);

    @GetMapping("/{customerId}/product/{productId}")
    @ApiOperation(value = "Check if the product exists in the customer's wish list")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = Boolean.class),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponseDTO.class)
    })
    ResponseEntity<?> checkIfTheProductExistsInTheCustomersWishList(@PathVariable String customerId, @PathVariable String productId);
}
