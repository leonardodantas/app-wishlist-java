package com.br.wishlist.repositorys;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;

import java.util.Optional;

public interface IWishListRepository {
    Optional<WishList> findByCustomerId(String customerId);
    WishList save(WishList newWishList);
    Optional<Product> findByUserIdAndProductsId(String customerId, String productId);
    void deleteByCustomerId(String customerId);
}
