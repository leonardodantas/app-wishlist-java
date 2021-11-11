package com.br.wishlist.services.impl;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.services.IFindProductsWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindProductsWishListService implements IFindProductsWishListService {

    @Autowired
    private IWishListRepository wishListRepository;

    @Override
    public WishListResponseDTO findAllProducts(String customerId) {
        WishList wishList = wishListRepository.findByCustomerId(customerId)
                .orElseGet(WishList::new);
        return WishListResponseDTO.from(wishList);
    }

    @Override
    public boolean checkIfTheProductExistsInTheCustomersWishList(String customerId, String productId) {
        Optional<Product> product = wishListRepository.findByUserIdAndProductsId(customerId, productId);
        return product.isPresent();
    }
}
