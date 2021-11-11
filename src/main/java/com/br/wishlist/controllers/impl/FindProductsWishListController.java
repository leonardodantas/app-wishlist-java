package com.br.wishlist.controllers.impl;

import com.br.wishlist.controllers.IFindProductsWishListController;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.services.IFindProductsWishListService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Api(tags = "WishList")
@RestController
@RequestMapping("/v1/find/customer/")
public class FindProductsWishListController implements IFindProductsWishListController {

    @Autowired
    private IFindProductsWishListService findProductsWishListService;

    @Override
    public ResponseEntity<?> findAllProducts(String customerId) {
        WishListResponseDTO response = findProductsWishListService.findAllProducts(customerId);
        if(Objects.isNull(response.getId())){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> checkIfTheProductExistsInTheCustomersWishList(String customerId, String productId) {
        boolean productExist = findProductsWishListService.checkIfTheProductExistsInTheCustomersWishList(customerId, productId);
        return ResponseEntity.ok(productExist);
    }
}
