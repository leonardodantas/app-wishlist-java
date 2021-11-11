package com.br.wishlist.controllers.impl;

import com.br.wishlist.controllers.IAddProductWishListController;
import com.br.wishlist.models.request.ProductRequestDTO;
import com.br.wishlist.models.response.ProductResponseDTO;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.services.IAddProductWishListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "WishList")
@RestController
@RequestMapping("/v1/add/product")
public class AddProductWishListController implements IAddProductWishListController {

    @Autowired
    private IAddProductWishListService addProductWishListService;

    @Override
    public ResponseEntity<?> addProduct(ProductRequestDTO request) {
        WishListResponseDTO response = addProductWishListService.addProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
