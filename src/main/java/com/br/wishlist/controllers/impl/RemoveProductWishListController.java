package com.br.wishlist.controllers.impl;

import com.br.wishlist.controllers.IRemoveProductWishListController;
import com.br.wishlist.services.IRemoveProductWishListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "WishList")
@RestController
@RequestMapping("/v1/remove/product")
public class RemoveProductWishListController implements IRemoveProductWishListController {

    @Autowired
    private IRemoveProductWishListService removeProductWishListService;

    @Override
    public ResponseEntity<?> removeProduct(String customerId, String productId) {
        removeProductWishListService.removeProduct(customerId, productId);
        return ResponseEntity.ok().build();
    }
}
