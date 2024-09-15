package com.foodorderBackend.foodbackend.Service;

import java.util.List;

import com.foodorderBackend.foodbackend.Exception.FoodException;
import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.Category;
import com.foodorderBackend.foodbackend.Model.Food;
import com.foodorderBackend.foodbackend.Model.Restaurant;
import com.foodorderBackend.foodbackend.Request.CreateFoodRequest;

public interface FoodService {

	
	public Food createFood(CreateFoodRequest req,Category category,
			   Restaurant restaurant) throws FoodException, RestaurantException;

    public void deleteFood(Long foodId) throws FoodException;
    
    
    public List<Food> getRestaurantsFood(Long restaurantId,
			boolean isVegetarian, boolean isNonveg, boolean isSeasonal,String foodCategory) throws FoodException;
    
    
     public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws FoodException;

	public Food updateAvailibilityStatus(Long foodId) throws FoodException;
    
    
}
