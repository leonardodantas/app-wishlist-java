package com.br.wishlist.services;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.repositorys.IWishListRepository;
import com.br.wishlist.services.impl.FindProductsWishListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindProductsWishListServiceTest {

    @InjectMocks
    private FindProductsWishListService findProductsWishListService;

    @Mock
    private IWishListRepository wishListRepository;

    private String customerId = "10";
    private String productId = "100";

    @Test
    public void testFindAllProducts() {

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(WishList.builder().products(new ArrayList<>()).build()));

        WishListResponseDTO wishListResponseDTO = findProductsWishListService.findAllProducts(customerId);

        assertNotNull(wishListResponseDTO);
    }

    @Test
    public void testCheckIfTheProductExistsInTheCustomersWishList() {

        when(wishListRepository.findByUserIdAndProductsId(customerId, productId))
                .thenReturn(Optional.of(Product.builder().build()));

        boolean productExist = findProductsWishListService.checkIfTheProductExistsInTheCustomersWishList(customerId, productId);

        assertTrue(productExist);
    }
}
