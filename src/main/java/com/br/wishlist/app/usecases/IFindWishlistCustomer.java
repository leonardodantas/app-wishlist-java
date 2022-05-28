package com.br.wishlist.app.usecases;

import com.br.wishlist.domain.WishList;

import java.util.Optional;

public interface IFindWishlistCustomer {
    Optional<WishList> execute(String customerId);
}
