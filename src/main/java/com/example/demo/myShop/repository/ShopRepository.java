package com.example.demo.myShop.repository;

import com.example.demo.myShop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
