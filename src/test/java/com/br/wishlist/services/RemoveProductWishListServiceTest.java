package com.br.wishlist.services;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.services.impl.RemoveProductWishListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductWishListServiceTest {

    @InjectMocks
    private RemoveProductWishListService removeProductWishListService;

    @Mock
    private IWishListRepository wishListRepository;

    private String customerId = "1";
    private String productId = "10";

    @Test(expected = ResponseStatusException.class)
    public void testCustomerWithoutWishList(){
        removeProductWishListService.removeProduct(customerId, productId);
    }

    @Test(expected = ResponseStatusException.class)
    public void testProductDoesNotExistInWishList() {

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new WishList()));

        removeProductWishListService.removeProduct(customerId, productId);

    }

    @Test
    public void testDeleteAllProducts(){

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().price(BigDecimal.valueOf(100)).quantity(10).id("10").build());

        WishList wishList = WishList.builder().customerId(customerId).products(products).build();

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(wishList));

        removeProductWishListService.removeProduct(customerId, productId);
        Mockito.verify(wishListRepository, Mockito.times(1)).deleteByCustomerId(customerId);

    }

    @Test
    public void testRemoveProduct() {

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().price(BigDecimal.valueOf(100)).quantity(10).id("10").build());
        products.add(Product.builder().price(BigDecimal.valueOf(100)).quantity(10).id("10").build());

        WishList wishList = WishList.builder().customerId(customerId).products(products).build();

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(wishList));

        removeProductWishListService.removeProduct(customerId, productId);
        Mockito.verify(wishListRepository, Mockito.times(1)).save(wishList);

    }
}
