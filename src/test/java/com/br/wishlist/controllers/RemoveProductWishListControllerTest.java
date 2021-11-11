package com.br.wishlist.controllers;

import com.br.wishlist.controllers.impl.RemoveProductWishListController;
import com.br.wishlist.services.IRemoveProductWishListService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductWishListControllerTest {

    @InjectMocks
    private RemoveProductWishListController removeProductWishListController;

    @Mock
    private IRemoveProductWishListService removeProductWishListService;

    private MockMvc mockMvc;

    @Before
    public void init(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(removeProductWishListController).build();
    }

    @Test
    public void testRemoveProduct() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("customerId", "1");
        params.add("productId", "12");

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/remove/product")
                .params(params)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

}
