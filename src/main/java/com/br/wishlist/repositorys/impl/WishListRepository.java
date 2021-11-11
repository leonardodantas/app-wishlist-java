package com.br.wishlist.repositorys.impl;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.repositorys.WishListSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public class WishListRepository implements IWishListRepository {

    @Autowired
    private WishListSpringData wishListSpringData;

    @Override
    public Optional<WishList> findByCustomerId(String customerId) {
        return wishListSpringData.findByCustomerId(customerId);
    }

    @Override
    public WishList save(WishList newWishList) {
        try {
            return wishListSpringData.save(newWishList);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<Product> findByUserIdAndProductsId(String customerId, String productId) {
        try {
            return wishListSpringData.findByCustomerIdAndProductsId(customerId, productId);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        try {
            wishListSpringData.deleteByCustomerId(customerId);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
