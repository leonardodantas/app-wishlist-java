package com.br.wishlist.app.usecases.impl;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.IRemoveProduct;
import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;
import org.springframework.stereotype.Service;

@Service
public class RemoveProduct implements IRemoveProduct {

    private static final String CUSTOMER_DOES_NOT_HAVE_WISH_LIST = "Customer does not have wish list";
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    private final IWishListRepository wishListRepository;

    public RemoveProduct(final IWishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public void execute(final String customerId, final String productId) {
        final var wishList = wishListRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException(CUSTOMER_DOES_NOT_HAVE_WISH_LIST));

        final var product = findProduct(productId, wishList);

        wishList.removeProduct(product);
        saveWishListAfterRemoval(wishList);
    }

    private Product findProduct(final String productId, final WishList wishList) {
        return wishList.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND));
    }

    private void saveWishListAfterRemoval(final WishList wishList) {
        if (wishList.containsProducts()) {
            wishListRepository.save(wishList);
        } else {
            wishListRepository.deleteByCustomerId(wishList.getCustomerId());
        }
    }
}
