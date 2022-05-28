package com.br.wishlist.app.usecases.impl;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.IFindWishlistCustomer;
import com.br.wishlist.domain.WishList;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindWishlistCustomer implements IFindWishlistCustomer {

    private final IWishListRepository wishListRepository;

    public FindWishlistCustomer(final IWishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Optional<WishList> execute(final String customerId) {
        return wishListRepository.findByCustomerId(customerId);
    }

}
