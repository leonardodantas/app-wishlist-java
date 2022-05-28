package com.br.wishlist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Document(value = "wishList")
public class WishList {

    @Id
    private String id;
    private String customerId;
    private BigDecimal amount;
    private int quantityItems;
    private List<Product> products;

    public WishList() {
        this.products = new ArrayList<>();
    }

    private WishList(final WishList wishList, final Product product) {
        this.id = wishList.getId();
        this.customerId = wishList.getCustomerId();
        this.quantityItems = wishList.getQuantityItems() + product.getQuantity();

        wishList.products.add(product);
        this.products = wishList.getProducts();

        this.adjustProducts();
    }

    public WishList(final String customerId, final Product product) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.quantityItems = product.getQuantity();
        this.products = Collections.singletonList(product);
        this.adjustProducts();
    }

    public static WishList of(final WishList wishList, final Product product) {
        return new WishList(wishList, product);
    }

    public static WishList of(final String customerId, final Product product) {
        return new WishList(customerId, product);
    }

    public void removeProduct(final Product product) {
        this.products.remove(product);
        this.calculateAmount();
        this.updateQuantityItems();
    }

    public void updateQuantityItems() {
        this.quantityItems = this.products.stream().map(Product::getQuantity).reduce(0, Integer::sum);
    }

    public void adjustProducts() {
        this.organizerProducts();
        this.calculateAmount();
        this.updateQuantityItems();
    }

    private void organizerProducts() {
        final var lastId = new ArrayList<>(products).get(0).getId();

        final var products = this.getProducts().stream()
                .filter(product -> product.getId().equals(lastId)).toList();

        if (products.size() > 1) {
            final var productToRemove = this.products.stream().filter(p -> p.getId().equals(lastId)).findFirst()
                    .orElse(new Product());
            this.removeProduct(productToRemove);
        }
    }

    public void calculateAmount() {
        this.amount = this.getProducts()
                .stream()
                .reduce(BigDecimal.ZERO, (partialResult, product) ->
                        partialResult.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))), BigDecimal::add);
    }

    public boolean containsProducts() {
        return this.products.size() > 0;
    }
}
