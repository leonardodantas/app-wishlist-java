package com.br.wishlist.services.impl;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.services.IRemoveProductWishListService;
import com.br.wishlist.utils.ValuesOrganizerWishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RemoveProductWishListService implements IRemoveProductWishListService {

    private static final String CUSTOMER_DOES_NOT_HAVE_WISH_LIST = "Customer does not have wish list";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    @Autowired
    private IWishListRepository wishListRepository;

    @Override
    public void removeProduct(String customerId, String productId) {
        WishList wishList = wishListRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, CUSTOMER_DOES_NOT_HAVE_WISH_LIST));

        Product product = wishList.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, PRODUCT_NOT_FOUND));

        wishList.removeProduct(product);
        saveWishListAfterRemoval(wishList);
    }

    private void saveWishListAfterRemoval(WishList wishList) {
        ValuesOrganizerWishList.addValuesProducts(wishList);
        if(wishList.getProducts().isEmpty()) {
            wishListRepository.deleteByCustomerId(wishList.getCustomerId());
         } else {
            wishList.updateQuantityItems();
            wishListRepository.save(wishList);
        }
    }
}
