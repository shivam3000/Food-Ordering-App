package com.foodorderBackend.foodbackend.Request;

import java.util.List;


import lombok.Data;

@Data
public class AddCartItemRequest {
	
	private Long menuItemId;
	private int quantity;
	private List<String> ingredients;

}