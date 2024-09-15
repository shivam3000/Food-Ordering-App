package com.foodorderBackend.foodbackend.Service;

import java.util.List;

import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.IngredientCategory;
import com.foodorderBackend.foodbackend.Model.IngredientsItem;

public interface IngredientsService {
	
	
	
	public IngredientCategory createIngredientsCategory(
			String name,Long restaurantId) throws RestaurantException;
	
	
	
	public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

	public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;
	
 
	
	public List<IngredientsItem> findRestaurantsIngredients(
			Long restaurantId);

	
	public IngredientsItem createIngredientsItem(Long restaurantId, 
			String ingredientName,Long ingredientCategoryId) throws Exception;

	public IngredientsItem updateStoke(Long id) throws Exception;
	
	

}
