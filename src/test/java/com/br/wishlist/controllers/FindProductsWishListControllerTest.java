package com.br.wishlist.controllers;

import com.br.wishlist.infra.controllers.FindProductsWishListController;
import com.br.wishlist.domain.WishList;
import com.br.wishlist.app.usecases.IFindWishlistCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FindProductsWishListControllerTest {

    @InjectMocks
    private FindProductsWishListController findProductsWishListController;

    @Mock
    private IFindWishlistCustomer findProductsWishListService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(findProductsWishListController).build();
    }

    @Test
    public void testFindWishlist() throws Exception {

        final var customerId = "1";

        final var wishlist = new WishList("1", "10", BigDecimal.valueOf(100), 10, List.of());
        when(findProductsWishListService.execute(customerId)).thenReturn(Optional.of(wishlist));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void testFindWishlistNoContent() throws Exception {

        final var customerId = "1";

        when(findProductsWishListService.execute(customerId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

    }

//    @Test
//    public void testCheckIfTheProductExistsInTheCustomersWishList() throws Exception {
//
//        String customerId = "1";
//        String productId = "2";
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/product/" + productId)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk());
//    }
}
