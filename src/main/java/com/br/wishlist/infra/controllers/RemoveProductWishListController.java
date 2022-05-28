package com.br.wishlist.infra.controllers;

import com.br.wishlist.app.usecases.IRemoveProduct;
import com.br.wishlist.infra.controllers.advice.response.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

@Api(tags = "WishList")
@RestController
@RequestMapping("/v1/remove/product")
public class RemoveProductWishListController {

    private final IRemoveProduct removeProduct;

    public RemoveProductWishListController(final IRemoveProduct removeProduct) {
        this.removeProduct = removeProduct;
    }

    @DeleteMapping
    @ApiOperation(value = "Remove product from wish list")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success"),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    public void execute(@RequestParam final String customerId, @RequestParam final String productId) {
        removeProduct.execute(customerId, productId);
    }
}
