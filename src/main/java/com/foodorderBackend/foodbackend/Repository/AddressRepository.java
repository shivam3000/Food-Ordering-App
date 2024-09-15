package com.foodorderBackend.foodbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorderBackend.foodbackend.Model.Address;



public interface AddressRepository extends JpaRepository<Address, Long> {

}
