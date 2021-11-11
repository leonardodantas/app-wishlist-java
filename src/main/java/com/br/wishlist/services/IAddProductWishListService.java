package com.br.wishlist.services;

import com.br.wishlist.models.request.ProductRequestDTO;
import com.br.wishlist.models.response.WishListResponseDTO;

public interface IAddProductWishListService {

    WishListResponseDTO addProduct(ProductRequestDTO productDTO);
}
