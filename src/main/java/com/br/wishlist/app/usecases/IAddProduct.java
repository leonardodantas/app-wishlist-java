package com.br.wishlist.app.usecases;

import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;

public interface IAddProduct {

    WishList execute(String customerId, Product product);
}
