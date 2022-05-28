package com.br.wishlist.services;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.impl.AddProduct;
import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AddProductWishListServiceTest {

    @InjectMocks
    private AddProduct addProductWishListService;

    @Mock
    private IWishListRepository wishListRepository;

    @Test
    public void testCreateWishList() {

        final var customerId = "1";

        final var product = Product.builder().id("10").quantity(10).price(BigDecimal.valueOf(100)).build();
        final var wishListSave = new WishList("10", customerId, BigDecimal.valueOf(1000), 10, List.of(product));

        when(wishListRepository.save(ArgumentMatchers.any()))
                .thenReturn(wishListSave);

        WishList wishList = addProductWishListService.execute(customerId, new Product());

        assertEquals(10, wishList.getQuantityItems());
        assertEquals(0, wishList.getAmount().compareTo(BigDecimal.valueOf(1000)));
        assertEquals("1", wishList.getCustomerId());
    }

    @Test(expected = RuntimeException.class)
    public void testWishListExceeded() {
        final var customerId = "1";
        final var product = Product.builder().id("10").quantity(21).price(BigDecimal.valueOf(100)).build();
        addProductWishListService.execute(customerId, product);

    }
}
