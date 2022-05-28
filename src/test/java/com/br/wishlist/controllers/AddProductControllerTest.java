package com.br.wishlist.controllers;

import com.br.wishlist.app.usecases.IAddProduct;
import com.br.wishlist.domain.WishList;
import com.br.wishlist.infra.controllers.AddProductController;
import com.br.wishlist.infra.converters.ProductConverter;
import com.br.wishlist.infra.jsons.requests.ProductRequestJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AddProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AddProductController addProductController;

    @Mock
    private IAddProduct addProduct;

    @Mock
    private ProductConverter productConverter;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(addProductController).build();
    }

    @Test
    public void testAddProduct() throws Exception {
        final var request = new ProductRequestJson("1", BigDecimal.TEN, 10);
        final var customerId = "1";

        when(addProduct.execute(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(new WishList());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/add/product/").param("customerId", customerId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(request))
        ).andExpect(status().isCreated());

    }
}
