package com.br.wishlist.utils;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.request.ProductRequestDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ValuesOrganizerWishList {

    public static void OrganizerProducts(WishList wishList, ProductRequestDTO productRequestDTO){
        List<Product> products = wishList.getProducts().stream()
                .filter(product -> product.getId().equals(productRequestDTO.getProductId()))
                .collect(Collectors.toList());

        if(products.size() > 1){
            Product product = Product.from(products.get(1));
            wishList.removeProducts(products);
            wishList.addProduct(product);
        }

        wishList.updateQuantityItems();
        addValuesProducts(wishList);
    }

    public static void addValuesProducts(WishList wishList) {

        BigDecimal amount = wishList.getProducts()
                .stream()
                .reduce(BigDecimal.ZERO, (partialResult, product) ->
                        partialResult.add(product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))), BigDecimal::add);

        wishList.setAmount(amount);
    }
}
