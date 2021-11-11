package com.br.wishlist.models.response;

import com.br.wishlist.models.entitys.WishList;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WishListResponseDTO {

    private final String id;
    private final String customerId;
    private final BigDecimal amount;
    private final int quantityItems;
    private final List<ProductResponseDTO> products;

    private WishListResponseDTO(WishList wishList) {
        this.id = wishList.getId();
        this.customerId = wishList.getCustomerId();
        this.amount = wishList.getAmount();
        this.quantityItems = wishList.getQuantityItems();
        this.products = wishList.getProducts().stream().map(ProductResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    public static WishListResponseDTO from(WishList wishList) {
        return new WishListResponseDTO(wishList);
    }
}
