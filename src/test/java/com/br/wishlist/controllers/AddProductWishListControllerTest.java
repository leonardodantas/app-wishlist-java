package com.br.wishlist.controllers;

import com.br.wishlist.controllers.impl.AddProductWishListController;
import com.br.wishlist.models.request.ProductRequestDTO;
import com.br.wishlist.services.IAddProductWishListService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddProductWishListControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AddProductWishListController addProductWishListController;

    @Mock
    private IAddProductWishListService addProductWishListService;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(addProductWishListController).build();
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductRequestDTO request = ProductRequestDTO.builder().productId("1").price(BigDecimal.TEN).quantity(10).customerId("10").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/add/product/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(request))
        ).andExpect(status().isCreated());

    }
}
