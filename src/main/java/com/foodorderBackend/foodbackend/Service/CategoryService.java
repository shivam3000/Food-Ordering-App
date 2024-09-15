package com.foodorderBackend.foodbackend.Service;

import java.util.List;

import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.Category;

public interface CategoryService {
	
	public Category createCategory (String name,Long userId) throws RestaurantException;
	public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
	public Category findCategoryById(Long id) throws RestaurantException;
	

}
