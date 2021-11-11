package com.br.wishlist.services;

import com.br.wishlist.models.response.WishListResponseDTO;

public interface IFindProductsWishListService {
    WishListResponseDTO findAllProducts(String customerId);
    boolean checkIfTheProductExistsInTheCustomersWishList(String customerId, String productId);
}
