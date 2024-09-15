package com.foodorderBackend.foodbackend.Service;

import java.util.List;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.IngredientCategory;
import com.foodorderBackend.foodbackend.Model.IngredientsItem;
import com.foodorderBackend.foodbackend.Model.Restaurant;
import com.foodorderBackend.foodbackend.Repository.IngredientsCategoryRepository;
import com.foodorderBackend.foodbackend.Repository.IngredientsItemRepository;



@Service 
public class IngredientsServiceImplementation  implements  IngredientsService  {
	
	
	
	
	@Autowired
	private IngredientsCategoryRepository ingredientsCategoryRepo;
	
	
	
	@Autowired
	private IngredientsItemRepository ingredientsItemRepository;
	
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	

	@Override
	public IngredientCategory createIngredientsCategory(String name, Long restaurantId) throws RestaurantException {
		IngredientCategory isExist=ingredientsCategoryRepo
				.findByRestaurantIdAndNameIgnoreCase(restaurantId,name);
		
		if(isExist!=null) {
			return isExist;
		}

		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory ingredientCategory=new IngredientCategory();
		ingredientCategory.setRestaurant(restaurant);
		ingredientCategory.setName(name);
		
		IngredientCategory createdCategory = ingredientsCategoryRepo.save(ingredientCategory);
		
		return createdCategory; 
	
	}

	@Override
	public IngredientCategory findIngredientsCategoryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<IngredientCategory> opt=ingredientsCategoryRepo.findById(id);
		if(opt.isEmpty()){
			throw new Exception("ingredient category not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
		return ingredientsCategoryRepo.findByRestaurantId(id);
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
		// TODO Auto-generated method stub
		return ingredientsItemRepository.findByRestaurantId(restaurantId);
	}

	@Override
	public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long ingredientCategoryId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientsItem updateStoke(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
