package com.br.wishlist.infra.database;

import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishListSpringData extends MongoRepository<WishList, String> {
    Optional<WishList> findByCustomerId(String customerId);
    Optional<Product> findByCustomerIdAndProductsId(String customerId, String productId);
    void deleteByCustomerId(String customerId);
}
