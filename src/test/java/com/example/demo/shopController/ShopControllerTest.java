package com.example.demo.shopController;

import com.example.demo.myShop.controller.ShopController;
import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.service.ShopService;

import com.example.demo.shopForTest.ShopForTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
@AutoConfigureMockMvc
public class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Test
    void getShopsTest() throws Exception {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        Shop shop1 = new Shop("Kyiv", "a1", "Books", 2, true);
        Shop shop2 = new Shop("Odessa", "b1", "Device", 5, true);

        List<Shop> expectedShopList = new ArrayList<>(Arrays.asList(shop, shop1, shop2));

        when(shopService.findAllShops()).thenReturn(expectedShopList);

        List<Shop> actualShopList = (List<Shop>) shopService.findAllShops();
        assertEquals(expectedShopList.size(), actualShopList.size());

        mockMvc.perform(MockMvcRequestBuilders.get("/getAllShops"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getShopByIdTest() throws Exception {
        long shopId = 1L;
        ShopForTest.shopForTest().setId(shopId);
        when(shopService.findById(shopId)).thenReturn(ShopForTest.shopForTest());
        mockMvc.perform(MockMvcRequestBuilders.get("/findById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(ShopForTest.shopForTest().getName()));
    }

    @Test
    void saveShopTest() throws Exception {
        when(shopService.save(any(Shop.class))).thenReturn(ShopForTest.shopForTest());

        mockMvc.perform(MockMvcRequestBuilders.post("/createShop")
                        .content(new ObjectMapper().writeValueAsString(ShopForTest.shopForTest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ShopForTest.shopForTest().getId()))
                .andDo(print());
    }

    @Test
    void updateShopTest() throws Exception {
        long id = 1L;
        Shop updatedShop = new Shop();
        updatedShop.setId(id);
        updatedShop.setName("Product");

        ShopForTest.shopForTest().setId(id);

        when(shopService.update(any(Shop.class), eq(updatedShop.getId()))).thenReturn(ShopForTest.shopForTest());

        mockMvc.perform(put("/update/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedShop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedShop.getName()))
                .andDo(print());


    }

    @Test
    void deleteShopTest() throws Exception {

        when(shopService.delete(ShopForTest.shopForTest().getId())).thenReturn(ShopForTest.shopForTest());

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/0")
                        .content(new ObjectMapper().writeValueAsString(ShopForTest.shopForTest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}