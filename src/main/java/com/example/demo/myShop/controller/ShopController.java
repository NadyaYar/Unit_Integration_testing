package com.example.demo.myShop.controller;

import com.example.demo.myShop.exception.ShopNotFoundException;
import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    @SneakyThrows
    @PostMapping(value = "/createShop")
    public Shop createShop(@RequestBody Shop shop) {
        return shopService.createShop(shop);
    }

    @SneakyThrows
    @GetMapping(value = "/getAllShops")
    public void getShops() {
    shopService.getShops();
    }

    @GetMapping("/findById/{id}")
    public Shop findShopById(@PathVariable("id") long id) throws ShopNotFoundException {
        return shopService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delShopById(@PathVariable("id") long id) throws ShopNotFoundException {
        shopService.delete(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Shop updateShop(@RequestBody Shop shop, @PathVariable Long id) throws ShopNotFoundException {
        return shopService.update(shop, id);
    }
}
