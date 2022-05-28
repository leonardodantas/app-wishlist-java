package com.br.wishlist.infra.jsons.responses;

import com.br.wishlist.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponse {

    private final String id;
    private final BigDecimal priceUnitary;
    private final int quantity;

    private ProductResponse(final Product product) {
        this.id = product.getId();
        this.priceUnitary = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public static ProductResponse from(final Product product) {
        return new ProductResponse(product);
    }
}
