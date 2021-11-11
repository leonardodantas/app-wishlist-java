package com.br.wishlist.controllers;

import com.br.wishlist.controllers.impl.FindProductsWishListController;
import com.br.wishlist.models.entitys.WishList;
import com.br.wishlist.models.response.WishListResponseDTO;
import com.br.wishlist.services.IFindProductsWishListService;
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

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FindProductsWishListControllerTest {

    @InjectMocks
    private FindProductsWishListController findProductsWishListController;

    @Mock
    private IFindProductsWishListService findProductsWishListService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(findProductsWishListController).build();
    }

    @Test
    public void testFindAllProducts() throws Exception {

        String customerId = "1";

        WishListResponseDTO response = WishListResponseDTO.from(WishList.builder().id("1").products(new ArrayList<>()).build());
        when(findProductsWishListService.findAllProducts(customerId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void testFindAllProductsWhereNoContent() throws Exception {

        String customerId = "1";

        WishListResponseDTO response = WishListResponseDTO.from(WishList.builder().products(new ArrayList<>()).build());
        when(findProductsWishListService.findAllProducts(customerId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

    }

    @Test
    public void testCheckIfTheProductExistsInTheCustomersWishList() throws Exception {

        String customerId = "1";
        String productId = "2";

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/product/" + productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
