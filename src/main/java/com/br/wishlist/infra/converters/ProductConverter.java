package com.br.wishlist.infra.converters;

import com.br.wishlist.domain.Product;
import com.br.wishlist.infra.jsons.requests.ProductRequestJson;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<ProductRequestJson, Product> {

    @Override
    public Product convert(final ProductRequestJson json) {
        return Product.builder()
                .id(json.productId())
                .price(json.price())
                .quantity(json.quantity())
                .build();
    }
}
