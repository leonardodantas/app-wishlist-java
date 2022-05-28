package com.br.wishlist.app.usecases.impl;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.ICheckIfProductExistsInCustomersWishList;
import com.br.wishlist.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckIfProductExistsInCustomersWishList implements ICheckIfProductExistsInCustomersWishList {
    private final IWishListRepository wishListRepository;

    public CheckIfProductExistsInCustomersWishList(final IWishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Optional<Product> execute(final String customerId, final String productId) {
        return wishListRepository.findByUserIdAndProductsId(customerId, productId);
    }
}
