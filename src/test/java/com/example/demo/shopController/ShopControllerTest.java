package com.example.demo.shopController;

import com.example.demo.myShop.controller.ShopController;
import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.service.ShopService;
import com.example.demo.shopForTest.ShopForTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
public class ShopControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShopService service;

//    @Test
//    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
//            throws Exception {
//
////        Shop alex = new Employee("alex");
//
//        List<Shop> allEmployees = List.of(ShopForTest.shopForTest());
//
//        given(service.getShops()).willReturn(allEmployees);
//
//        mvc.perform(get("/api/employees")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(alex.getName())));
//    }
}
