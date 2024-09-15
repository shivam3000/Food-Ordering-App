package com.foodorderBackend.foodbackend.Request;
import com.foodorderBackend.foodbackend.Model.Address;

import lombok.Data;

@Data
public class CreateOrderRequest {
 
	private Long restaurantId;
	
	private Address deliveryAddress;
	
    
}
