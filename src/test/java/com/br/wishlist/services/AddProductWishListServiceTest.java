package com.br.wishlist.services;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.request.ProductRequestDTO;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.services.impl.AddProductWishListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AddProductWishListServiceTest {

    @InjectMocks
    private AddProductWishListService addProductWishListService;

    @Mock
    private IWishListRepository wishListRepository;

    @Test
    public void testCreateWishList() {

        String productId = "10";
        String customerId = "1";

        ProductRequestDTO product = ProductRequestDTO.builder().productId(productId).price(BigDecimal.valueOf(100)).quantity(10).customerId(customerId).build();

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id("10").quantity(10).price(BigDecimal.valueOf(100)).build());
        WishList wishListSave = WishList.builder().customerId("1").amount(BigDecimal.valueOf(1000)).quantityItems(10).products(products).build();
        when(wishListRepository.save(ArgumentMatchers.any()))
                .thenReturn(wishListSave);

        WishListResponseDTO wishListResponseDTO = addProductWishListService.addProduct(product);

        assertEquals(10, wishListResponseDTO.getQuantityItems());
        assertEquals(0, wishListResponseDTO.getAmount().compareTo(BigDecimal.valueOf(1000)));
        assertEquals("1", wishListResponseDTO.getCustomerId());
    }

    @Test(expected = ResponseStatusException.class)
    public void testWishListExceeded() {

        String productId = "10";
        String customerId = "1";

        ProductRequestDTO product = ProductRequestDTO.builder().productId(productId).price(BigDecimal.valueOf(100)).quantity(21).customerId(customerId).build();

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id("10").quantity(21).price(BigDecimal.valueOf(100)).build());
        WishList wishListSave = WishList.builder().customerId("1").amount(BigDecimal.valueOf(1000)).quantityItems(10).products(products).build();

        addProductWishListService.addProduct(product);

    }
}
