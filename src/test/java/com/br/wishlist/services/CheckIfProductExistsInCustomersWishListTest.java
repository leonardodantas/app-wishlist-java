package com.br.wishlist.services;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.impl.CheckIfProductExistsInCustomersWishList;
import com.br.wishlist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckIfProductExistsInCustomersWishListTest {


    @InjectMocks
    private CheckIfProductExistsInCustomersWishList checkIfProductExistsInCustomersWishList;

    @Mock
    private IWishListRepository wishListRepository;
    private final String customerId = "10";
    private final String productId = "100";

    @Test
    public void testProductExists() {
        final var customerId = "10";
        final var productId = "100";

        when(checkIfProductExistsInCustomersWishList.execute(customerId, productId))
                .thenReturn(Optional.of(Product.builder().build()));

        final var product = checkIfProductExistsInCustomersWishList.execute(customerId, productId);

        assertTrue(product.isPresent());
    }

    @Test
    public void testProductNotExists() {
        final var product = checkIfProductExistsInCustomersWishList.execute(customerId, productId);
        assertTrue(product.isEmpty());
    }

}
