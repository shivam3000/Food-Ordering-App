package com.foodorderBackend.foodbackend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodorderBackend.foodbackend.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	 Optional<Cart> findByCustomer_Id(Long userId);
}
