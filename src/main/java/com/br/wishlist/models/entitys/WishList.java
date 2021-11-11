package com.br.wishlist.models.entitys;

import com.br.wishlist.models.request.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@Document(value = "wish-list-user")
public class WishList {

    @Id
    private String id;
    private String customerId;
    @Setter
    private BigDecimal amount;
    private int quantityItems;
    private List<Product> products;

    public WishList(){
        this.products = new ArrayList<>();
    }

    private WishList(WishList wishList, ProductRequestDTO productDTO) {
        this.id = wishList.getId();
        this.customerId = wishList.getCustomerId();
        this.quantityItems = wishList.getQuantityItems() + productDTO.getQuantity();
        wishList.products.add(Product.from(productDTO));
        this.products = wishList.getProducts();
    }

    private WishList(ProductRequestDTO productDTO) {
        this.id = UUID.randomUUID().toString();
        this.customerId = productDTO.getCustomerId();
        this.quantityItems = productDTO.getQuantity();
        this.products = Collections.singletonList(Product.from(productDTO));
    }

    public static WishList from(ProductRequestDTO productDTO) {
        return new WishList(productDTO);
    }

    public static WishList of(WishList wishList, ProductRequestDTO productDTO) {
        return new WishList(wishList, productDTO);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void removeProducts(List<Product> products) {
        this.products.removeAll(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void updateQuantityItems() {
        this.quantityItems = this.products.stream().map(Product::getQuantity).reduce(0, Integer::sum);
    }
}
