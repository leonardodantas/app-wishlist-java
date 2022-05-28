package com.br.wishlist.app.usecases.impl;

import com.br.wishlist.app.usecases.IAddProduct;
import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;
import com.br.wishlist.app.repositories.IWishListRepository;
import org.springframework.stereotype.Service;

@Service
public class AddProduct implements IAddProduct {

    private static final String MAXIMUM_WISH_LIST_SIZE_EXCEEDED = "Maximum Wish List Size Exceeded";

    private final IWishListRepository wishListRepository;

    public AddProduct(final IWishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public WishList execute(final String customerId, final Product product) {
        final var wishList = this.preparationOfWishListToSave(customerId, product);
        this.validateSizeOfWishList(wishList);
        return wishListRepository.save(wishList);
    }

    private WishList preparationOfWishListToSave(final String customerId, final Product product) {
        return wishListRepository.findByCustomerId(customerId)
                .map(wishList -> WishList.of(wishList, product))
                .orElse(WishList.of(customerId, product));
    }

    private void validateSizeOfWishList(final WishList wishList) {
        if (wishList.getQuantityItems() > 20) {
            throw new RuntimeException(MAXIMUM_WISH_LIST_SIZE_EXCEEDED);
        }
    }
}
