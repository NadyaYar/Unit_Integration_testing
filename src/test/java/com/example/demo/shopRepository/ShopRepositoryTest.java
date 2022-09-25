package com.example.demo.shopRepository;

import com.example.demo.myShop.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void addShopTest(){

    }
}
