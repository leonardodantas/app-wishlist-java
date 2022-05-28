package com.br.wishlist.infra.controllers;

import com.br.wishlist.app.usecases.ICheckIfProductExistsInCustomersWishList;
import com.br.wishlist.infra.controllers.advice.response.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

@Api(tags = "WishList")
@RestController
@RequestMapping("/v1/find/customer/")
public class CheckIfTheProductExistsController {

    private final ICheckIfProductExistsInCustomersWishList checkIfTheProductExistsInTheCustomersWishList;

    public CheckIfTheProductExistsController(final ICheckIfProductExistsInCustomersWishList checkIfTheProductExistsInTheCustomersWishList) {
        this.checkIfTheProductExistsInTheCustomersWishList = checkIfTheProductExistsInTheCustomersWishList;
    }

    @GetMapping("/{customerId}/product/{productId}")
    @ApiOperation(value = "Check if the product exists in the customer's wish list")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = Boolean.class),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponse.class)
    })
    public ResponseEntity<?> execute(@PathVariable final String customerId, @PathVariable final String productId) {
        final var product = checkIfTheProductExistsInTheCustomersWishList.execute(customerId, productId);
        return product.map(p -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.noContent().build());
    }
}
