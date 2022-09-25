package com.example.demo.shopService;

import com.example.demo.myShop.exception.ShopExistException;
import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.repository.ShopRepository;
import com.example.demo.myShop.service.ShopService;
import com.example.demo.shopForTest.ShopForTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService shopService;

    @Test
    void saveTest() throws ShopExistException {
        when(shopRepository.save(ShopForTest.shopForTest())).thenReturn(ShopForTest.shopForTest());

        Shop actualShop = shopService.createShop(ShopForTest.shopForTest());

        assertEquals(actualShop.getId(), ShopForTest.shopForTest().getId());
    }

    @Test
    void getShopByIdTest() throws ShopNotFoundException {

        long shopId = 3L;
        ShopForTest.shopForTest().setId(shopId);

        when(shopRepository.existsById(ShopForTest.shopForTest().getId())).thenReturn(true);
        when(shopRepository.findById(shopId)).thenReturn(Optional.of(ShopForTest.shopForTest()));

        Shop actualShop = shopService.findById(shopId);

        assertEquals("Product", actualShop.getName());
        assertEquals(13, actualShop.getNumberOfStaff());
        assertEquals(true, actualShop.getIsHasSite());
        assertEquals("Lviv", actualShop.getCity());
        assertEquals("d1", actualShop.getStreet());
    }

    @Test
    void getAllShopsTest() {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        Shop shop1 = new Shop("Kyiv", "a1", "Books", 2, true);
        Shop shop2 = new Shop("Odessa", "b1", "Device", 5, true);
        Shop shop3 = new Shop("Odessa", "b1", "Device", 5, true);
        Shop shop4 = new Shop("Odessa", "b1", "Device", 5, true);

        List<Shop> expectedShopList = new ArrayList<>(Arrays.asList(shop, shop1, shop2, shop3, shop4));

        when(shopRepository.findAll()).thenReturn(expectedShopList);

        List<Shop> actualShopList = (List<Shop>) shopService.getShops();
        assertEquals(expectedShopList.size(), actualShopList.size());
    }

    @Test
    void updateShopTest() throws ShopNotFoundException {
        long shopId = 3L;
        ShopForTest.shopForTest().setId(shopId);
        when(shopRepository.existsById(ShopForTest.shopForTest().getId())).thenReturn(true);
        when(shopRepository.findById(shopId)).thenReturn(Optional.of(ShopForTest.shopForTest()));

        Shop actualShop = shopService.update(ShopForTest.shopForTest(), shopId);

        assertEquals(actualShop.getName(), ShopForTest.shopForTest().getName());
    }

    @Test
    void deleteTest() throws ShopNotFoundException {
        long shopId = 3L;
        ShopForTest.shopForTest().setId(shopId);
        when(shopRepository.existsById(ShopForTest.shopForTest().getId())).thenReturn(true);

        Shop actualShop = shopService.delete(shopId);
        assertThat(actualShop).isNull();
    }

    @Test
    void shopNotFoundExceptionTest() {
        ShopForTest.shopForTest().setId(0L);

        assertThrows(ShopNotFoundException.class, () -> shopService.isExists(5L));
    }
}
