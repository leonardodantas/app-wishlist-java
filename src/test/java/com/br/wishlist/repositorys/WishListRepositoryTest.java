package com.br.wishlist.repositorys;

import com.br.wishlist.models.entitys.Product;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.repositorys.impl.WishListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WishListRepositoryTest {

    @Mock
    private WishListSpringData wishListSpringData;

    @InjectMocks
    private WishListRepository wishListRepository;

    @Test
    public void testFindByCustomerId(){
        String customerId = "1";

        when(wishListSpringData.findByCustomerId(customerId))
                .thenReturn(Optional.of(WishList.builder().customerId(customerId).build()));

        Optional<WishList> wishList = wishListRepository.findByCustomerId(customerId);
        assertEquals(customerId, wishList.get().getCustomerId());
    }

    @Test
    public void testSave(){

        String id = "1";
        WishList wishListSave = WishList.builder().id(id).build();

        when(wishListSpringData.save(wishListSave))
                .thenReturn(wishListSave);

        WishList save = wishListRepository.save(wishListSave);

        assertEquals(save.getId(), wishListSave.getId());
    }

    @Test
    public void testFindByUserIdAndProductsId() {

        String customerId = "1";
        String productId = "21";

        when(wishListSpringData.findByCustomerIdAndProductsId(customerId, productId))
                .thenReturn(Optional.of(Product.builder().id(productId).build()));

        Optional<Product> product = wishListRepository.findByUserIdAndProductsId(customerId, productId);

        assertEquals(productId, product.get().getId());
    }

    @Test
    public void testDeleteByCustomerId() {

        String customerId = "1";
        wishListRepository.deleteByCustomerId(customerId);
        Mockito.verify(wishListSpringData, Mockito.times(1)).deleteByCustomerId(customerId);

    }
}
