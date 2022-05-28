package com.br.wishlist.controllers;

import com.br.wishlist.app.usecases.ICheckIfProductExistsInCustomersWishList;
import com.br.wishlist.domain.Product;
import com.br.wishlist.infra.controllers.CheckIfTheProductExistsController;
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
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CheckIfTheProductExistsControllerTest {

    @InjectMocks
    private CheckIfTheProductExistsController checkIfTheProductExistsController;

    @Mock
    private ICheckIfProductExistsInCustomersWishList checkIfTheProductExistsInTheCustomersWishList;

    private MockMvc mockMvc;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(checkIfTheProductExistsController).build();
    }

    @Test
    public void textProductExist() throws Exception {

        final var customerId = "1";
        final var productId = "10";

        final var product = new Product("1", BigDecimal.TEN, 10);

        when(checkIfTheProductExistsInTheCustomersWishList.execute(customerId, productId)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/product/" + productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void textProductNoContent() throws Exception {

        final var customerId = "1";
        final var productId = "10";

        when(checkIfTheProductExistsInTheCustomersWishList.execute(customerId, productId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/find/customer/" + customerId + "/product/" + productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

    }
}
