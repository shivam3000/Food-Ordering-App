package com.foodorderBackend.foodbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorderBackend.foodbackend.Model.OrderItem;



public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
