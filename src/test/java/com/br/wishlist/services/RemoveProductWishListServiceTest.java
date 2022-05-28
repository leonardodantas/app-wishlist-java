package com.br.wishlist.services;

import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.impl.RemoveProduct;
import com.br.wishlist.domain.Product;
import com.br.wishlist.domain.WishList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductWishListServiceTest {

    @InjectMocks
    private RemoveProduct removeProductWishListService;

    @Mock
    private IWishListRepository wishListRepository;

    private final String customerId = "1";
    private final String productId = "10";

    @Test(expected = RuntimeException.class)
    public void testCustomerWithoutWishList() {
        removeProductWishListService.execute(customerId, productId);
    }

    @Test(expected = RuntimeException.class)
    public void testProductDoesNotExistInWishList() {

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new WishList()));

        removeProductWishListService.execute(customerId, productId);

    }

    @Test
    public void testRemoveAllProducts() {
        final var product = Product.builder().price(BigDecimal.valueOf(100)).quantity(10).id("10").build();

        final var products = new ArrayList<Product>();
        products.add(product);

        final var wishList = new WishList("1", customerId, BigDecimal.TEN, 10, products);

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(wishList));

        removeProductWishListService.execute(customerId, productId);
        Mockito.verify(wishListRepository, Mockito.times(1)).deleteByCustomerId(customerId);

    }

    @Test
    public void testRemoveProduct() {

        final var products = new ArrayList<Product>();
        products.add(Product.builder().price(BigDecimal.valueOf(100)).quantity(10).id("10").build());
        products.add(Product.builder().price(BigDecimal.valueOf(100)).quantity(10).id("10").build());

        final var wishList = new WishList("1", customerId, BigDecimal.TEN, 10, products);

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(wishList));

        removeProductWishListService.execute(customerId, productId);
        Mockito.verify(wishListRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }
}
