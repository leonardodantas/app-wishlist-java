package com.br.wishlist.models.response;

import com.br.wishlist.models.entitys.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponseDTO {

    private final String id;
    private final BigDecimal priceUnitary;
    private final int quantity;

    private ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.priceUnitary = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public static ProductResponseDTO from(Product product) {
        return new ProductResponseDTO(product);
    }
}
