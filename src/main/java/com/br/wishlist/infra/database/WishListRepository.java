package com.br.wishlist.infra.database;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WishListRepository implements IWishListRepository {

    private final WishListSpringData wishListSpringData;

    public WishListRepository(final WishListSpringData wishListSpringData) {
        this.wishListSpringData = wishListSpringData;
    }

    @Override
    public Optional<WishList> findByCustomerId(final String customerId) {
        return wishListSpringData.findByCustomerId(customerId);
    }

    @Override
    public WishList save(final WishList wishList) {
        try {
            return wishListSpringData.save(wishList);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findByUserIdAndProductsId(final String customerId, final String productId) {
        return wishListSpringData.findByCustomerIdAndProductsId(customerId, productId);
    }

    @Override
    public void deleteByCustomerId(final String customerId) {
        try {
            wishListSpringData.deleteByCustomerId(customerId);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
