package com.example.demo.shopForTest;

import com.example.demo.myShop.model.Shop;

public class ShopForTest {

    public static Shop shopForTest() {
        Shop shop = new Shop("Lviv", "d1", "Product", 13, true);
        shop.setId(1L);
        return shop;
    }
}
