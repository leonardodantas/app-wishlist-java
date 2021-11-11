package com.br.wishlist.services.impl;

import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.request.ProductRequestDTO;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.services.IAddProductWishListService;
import com.br.wishlist.utils.ValuesOrganizerWishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AddProductWishListService implements IAddProductWishListService {

    private static final String MAXIMUM_WISH_LIST_SIZE_EXCEEDED = "Maximum Wish List Size Exceeded";

    @Autowired
    private IWishListRepository wishListRepository;

    @Override
    public WishListResponseDTO addProduct(ProductRequestDTO productDTO) {
        WishList wishList = preparationOfWishListToSave(productDTO);
        ValuesOrganizerWishList.OrganizerProducts(wishList, productDTO);
        validateSizeOfWishList(wishList);
        WishList wishListSave = wishListRepository.save(wishList);
        return WishListResponseDTO.from(wishListSave);
    }

    private WishList preparationOfWishListToSave(ProductRequestDTO productDTO){
        Optional<WishList> wishList = wishListRepository.findByCustomerId(productDTO.getCustomerId());

        if(wishList.isEmpty()) {
            return WishList.from(productDTO);
        }

        return WishList.of(wishList.get(), productDTO);
    }

    private void validateSizeOfWishList(WishList wishList){
        wishList.updateQuantityItems();
        if(wishList.getQuantityItems() > 20){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MAXIMUM_WISH_LIST_SIZE_EXCEEDED);
        }
    }
}
