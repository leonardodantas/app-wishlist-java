package com.br.wishlist.services;

import com.br.wishlist.domain.WishList;
import com.br.wishlist.app.repositories.IWishListRepository;
import com.br.wishlist.app.usecases.impl.FindWishlistCustomer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindProductsWishListServiceTest {

    @InjectMocks
    private FindWishlistCustomer findProductsWishListService;

    @Mock
    private IWishListRepository wishListRepository;

    private String customerId = "10";
    private String productId = "100";

    @Test
    public void testFindAllProducts() {

        when(wishListRepository.findByCustomerId(customerId))
                .thenReturn(Optional.of(new WishList()));

        final var wishListResponse = findProductsWishListService.execute(customerId);

        assertNotNull(wishListResponse);
    }

//    @Test
//    public void testCheckIfTheProductExistsInTheCustomersWishList() {
//
//        when(wishListRepository.findByUserIdAndProductsId(customerId, productId))
//                .thenReturn(Optional.of(Product.builder().build()));
//
//        boolean productExist = findProductsWishListService.checkIfTheProductExistsInTheCustomersWishList(customerId, productId);
//
//        assertTrue(productExist);
//    }
}
