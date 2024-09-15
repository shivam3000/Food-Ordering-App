package com.foodorderBackend.foodbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorderBackend.foodbackend.Model.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {


//    CartItem findByFoodIsContaining

}
