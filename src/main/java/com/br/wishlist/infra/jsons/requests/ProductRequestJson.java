package com.br.wishlist.infra.jsons.requests;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductRequestJson(
        @NotBlank
        String productId,
        @DecimalMin(value = "1") @NotNull
        BigDecimal price,
        @Min(value = 1)
        int quantity
) {

}
