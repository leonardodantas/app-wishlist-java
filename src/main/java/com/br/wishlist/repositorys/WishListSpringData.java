package com.br.wishlist.repositorys;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishListSpringData extends MongoRepository<WishList, String> {
    Optional<WishList> findByCustomerId(String customerId);
    Optional<Product> findByCustomerIdAndProductsId(String customerId, String productId);
    void deleteByCustomerId(String customerId);
}
