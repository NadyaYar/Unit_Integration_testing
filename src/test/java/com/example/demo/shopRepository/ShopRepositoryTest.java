package com.example.demo.shopRepository;

import com.example.demo.myShop.model.Shop;
import com.example.demo.myShop.repository.ShopRepository;
import com.example.demo.shopForTest.ShopForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ShopRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void saveShopTest() {

        entityManager.persist(ShopForTest.shopForTest());
        entityManager.flush();

        assertThat(shopRepository.findById(ShopForTest.shopForTest().getId())
                .get()).isEqualTo(ShopForTest.shopForTest());
    }

    @Test
    void findAllShopsTest() {
        shopRepository.save(ShopForTest.shopForTest());
        List<Shop> shopsResult = new ArrayList<>(shopRepository.findAll());
        assertEquals(shopsResult.size(), 1);
    }
}
