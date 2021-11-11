package com.br.wishlist.models.entitys;

import com.br.wishlist.models.request.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Builder
@Document(value = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private BigDecimal price;
    private int quantity;

    private Product(ProductRequestDTO productDTO) {
        this.id = productDTO.getProductId();
        this.price = productDTO.getPrice();
        this.quantity = productDTO.getQuantity();
    }

    private Product(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public static Product from(ProductRequestDTO productDTO) {
        return new Product(productDTO);
    }

    public static Product from(Product product) {
        return new Product(product);
    }
}
