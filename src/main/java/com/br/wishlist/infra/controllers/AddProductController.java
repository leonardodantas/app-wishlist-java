package com.br.wishlist.infra.controllers;

import com.br.wishlist.app.usecases.IAddProduct;
import com.br.wishlist.infra.converters.ProductConverter;
import com.br.wishlist.infra.jsons.requests.ProductRequestJson;
import com.br.wishlist.infra.controllers.advice.response.ErrorResponse;
import com.br.wishlist.infra.jsons.responses.WishListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "WishList")
@RestController
@RequestMapping("/v1/add/product")
public class AddProductController {

    private final IAddProduct addProduct;
    private final ProductConverter converter;

    public AddProductController(final IAddProduct addProduct, final ProductConverter converter) {
        this.addProduct = addProduct;
        this.converter = converter;
    }

    @PostMapping
    @ApiOperation(tags = "WishList", value = "Add product to wish list")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Success", response = WishListResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_CONFLICT, message = "Error"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Error", response = ErrorResponse.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    public WishListResponse execute(@Valid @RequestBody final ProductRequestJson request, @RequestParam final String customerId) {
        final var response = addProduct.execute(customerId, converter.convert(request));
        return WishListResponse.from(response);
    }
}
