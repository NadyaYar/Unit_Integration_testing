package com.example.demo.shopService;

import com.example.demo.myShop.exception.ShopExistException;
import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.repository.ShopRepository;
import com.example.demo.myShop.service.ShopService;
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
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        when(shopRepository.save(shop)).thenReturn(shop);

        Shop shopSave = shopService.createShop(shop);

        assertEquals(shopSave.getId(), shop.getId());
    }

    @Test
    void getShopByIdTest() throws ShopNotFoundException {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        long shopId = 3L;
        shop.setId(shopId);

        when(shopRepository.existsById(shop.getId())).thenReturn(true);
        when(shopRepository.findById(shopId)).thenReturn(Optional.of(shop));

        Shop shopFind = shopService.findById(shopId);

        assertEquals("Product", shopFind.getName());
        assertEquals(13, shopFind.getNumberOfStaff());
        assertEquals(true, shopFind.getIsHasSite());
        assertEquals("Lviv", shopFind.getCity());
        assertEquals("d1", shopFind.getStreet());
    }

    @Test
    void getAllShopsTest() {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        Shop shop1 = new Shop("Kyiv", "a1", "Books", 2, true);
        Shop shop2 = new Shop("Odessa", "b1", "Device", 5, true);
        Shop shop3 = new Shop("Odessa", "b1", "Device", 5, true);
        Shop shop4 = new Shop("Odessa", "b1", "Device", 5, true);

        List<Shop> shops = new ArrayList<>(Arrays.asList(shop, shop1, shop2, shop3, shop4));

        when(shopRepository.findAll()).thenReturn(shops);

        List<Shop> shopList = (List<Shop>) shopService.getShops();
        assertEquals(5, shopList.size());
    }

    @Test
    void updateShopTest() throws ShopNotFoundException {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        long shopId = 3L;
        shop.setId(shopId);
        when(shopRepository.existsById(shop.getId())).thenReturn(true);
        when(shopRepository.findById(shopId)).thenReturn(Optional.of(shop));

        Shop shopToUpdate = shopService.update(shop, shopId);

        assertEquals(shopToUpdate.getName(), shop.getName());
    }

    @Test
    void deleteTest() throws ShopNotFoundException {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        long shopId = 3L;
        shop.setId(shopId);
        when(shopRepository.existsById(shop.getId())).thenReturn(true);

        Shop shopDel = shopService.delete(shopId);
        assertThat(shopDel).isNull();
    }

    @Test
    void shopExistExceptionTest() throws ShopExistException {
        Throwable exception = assertThrows(ShopExistException.class, () -> {
            throw new ShopExistException("Shop already exist");
        });

        assertEquals("Shop already exist", exception.getMessage());
    }

    @Test
    void shopNotFoundExceptionTest() {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        shop.setId(0L);

        assertThrows(ShopNotFoundException.class, () -> shopService.isExists(5L));
    }
}
