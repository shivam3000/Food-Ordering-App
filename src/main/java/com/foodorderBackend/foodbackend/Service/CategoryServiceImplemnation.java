package com.foodorderBackend.foodbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.Category;
import com.foodorderBackend.foodbackend.Model.Restaurant;
import com.foodorderBackend.foodbackend.Repository.CategoryRepository;

@Service
public class CategoryServiceImplemnation  implements CategoryService   {
	
	@Autowired
   private 	CategoryRepository categoryRepository ; 
	
	@Autowired
	 private RestaurantService    restaurantService  ;
	

	@Override
	public Category createCategory(String name, Long userId) throws RestaurantException {
		Restaurant restaurant=restaurantService.getRestaurantsByUserId(userId);
		Category createdCategory=new Category();
		createdCategory.setName(name);
		createdCategory.setRestaurant(restaurant);
		return categoryRepository.save(createdCategory);
		
	
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException {
		Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
		return categoryRepository.findByRestaurantId(restaurantId);
	}
		
		
		
		
	
	@Override
	public Category findCategoryById(Long id) throws RestaurantException {
       Optional<Category> opt=categoryRepository.findById(id);
		if(opt.isEmpty()) {
			throw new RestaurantException("category not exist with id "+id);
		}
		return opt.get();
	}
	

}
