package com.foodorderBackend.foodbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodorderBackend.foodbackend.Model.IngredientCategory;



public interface IngredientsCategoryRepository extends JpaRepository<IngredientCategory, Long>{
	
	
//	List<IngredientCategory> findByFoodId(Long menuItemId);
	List<IngredientCategory> findByRestaurantId(Long id);
	
	
	@Query("SELECT e FROM IngredientCategory e "
			+ "WHERE e.restaurant.id = :restaurantId "
			+ "AND lower(e.name) = lower(:name)")
	IngredientCategory findByRestaurantIdAndNameIgnoreCase(
			@Param("restaurantId") Long restaurantId, @Param("name") String name);
}
