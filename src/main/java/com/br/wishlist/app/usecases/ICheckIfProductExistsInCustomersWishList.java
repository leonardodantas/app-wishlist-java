package com.br.wishlist.app.usecases;

import com.br.wishlist.domain.Product;

import java.util.Optional;

public interface ICheckIfProductExistsInCustomersWishList {

    Optional<Product> execute(String customerId, String productId);
}
