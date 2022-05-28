package com.br.wishlist.infra.jsons.responses;

import com.br.wishlist.domain.WishList;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WishListResponse {

    private final String id;
    private final String customerId;
    private final BigDecimal amount;
    private final int quantityItems;
    private final List<ProductResponse> products;

    private WishListResponse(final WishList wishList) {
        this.id = wishList.getId();
        this.customerId = wishList.getCustomerId();
        this.amount = wishList.getAmount();
        this.quantityItems = wishList.getQuantityItems();
        this.products = wishList.getProducts().stream().map(ProductResponse::from).collect(Collectors.toUnmodifiableList());
    }

    public static WishListResponse from(final WishList wishList) {
        return new WishListResponse(wishList);
    }
}
