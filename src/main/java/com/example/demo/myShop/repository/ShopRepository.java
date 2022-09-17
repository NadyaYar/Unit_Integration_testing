package com.example.demo.myShop.repository;

import com.example.demo.myShop.model.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Long> {
}
