package com.br.wishlist.app.repositories;

import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;

import java.util.Optional;

public interface IWishListRepository {
    Optional<WishList> findByCustomerId(String customerId);
    WishList save(WishList wishList);
    Optional<Product> findByUserIdAndProductsId(String customerId, String productId);
    void deleteByCustomerId(String customerId);
}
