package com.br.wishlist.utils;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.request.ProductRequestDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ValuesOrganizerWishListTest {

    private WishList wishList;

    @Before
    public void init(){
        wishList = getWishListForTest();
    }
    @Test
    public void testOrganizerProducts(){

        ProductRequestDTO products = ProductRequestDTO.builder().productId("2").quantity(1).price(BigDecimal.valueOf(200)).build();
        ValuesOrganizerWishList.OrganizerProducts(wishList, products);
        assertEquals(2,wishList.getQuantityItems());
    }

    @Test
    public void testAddValuesProducts(){

        ValuesOrganizerWishList.addValuesProducts(wishList);
        assertEquals(0, wishList.getAmount().compareTo(BigDecimal.valueOf(350)));

    }

    private WishList getWishListForTest() {
        return WishList.builder()
                .products(
                        new ArrayList<>(List.of(Product.builder().id("1").quantity(1).price(BigDecimal.valueOf(50)).build(),
                                Product.builder().id("2").quantity(1).price(BigDecimal.valueOf(100)).build(),
                                Product.builder().id("2").quantity(1).price(BigDecimal.valueOf(200)).build())))
                .build();
    }
}
