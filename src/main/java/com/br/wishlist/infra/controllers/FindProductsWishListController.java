package com.br.wishlist.infra.controllers;

import com.br.wishlist.app.usecases.IFindWishlistCustomer;
import com.br.wishlist.infra.controllers.advice.response.ErrorResponse;
import com.br.wishlist.infra.jsons.responses.ProductResponse;
import com.br.wishlist.infra.jsons.responses.WishListResponse;
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
public class FindProductsWishListController {

    private final IFindWishlistCustomer findWishlistCustomer;

    public FindProductsWishListController(final IFindWishlistCustomer findWishlistCustomer) {
        this.findWishlistCustomer = findWishlistCustomer;
    }

    @GetMapping("/{customerId}/products")
    @ApiOperation(value = "Search all user products")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = ProductResponse.class, responseContainer = "List"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "No content"),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponse.class)
    })
    public ResponseEntity<?> execute(@PathVariable final String customerId) {
        final var response = findWishlistCustomer.execute(customerId);
        return response.map(wishList -> ResponseEntity.ok(WishListResponse.from(wishList)))
                .orElse(ResponseEntity.noContent().build());
    }

}
