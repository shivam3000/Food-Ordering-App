package com.foodorderBackend.foodbackend.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorderBackend.foodbackend.Model.User;



public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String username) ; 

}
