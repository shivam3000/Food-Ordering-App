package com.foodorderBackend.foodbackend.Service;

import java.util.List;

import com.foodorderBackend.foodbackend.Dto.RestaurantDto;
import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.Restaurant;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Request.CreateRestaurantRequest;




public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
			throws RestaurantException;

	public void deleteRestaurant(Long restaurantId) throws RestaurantException;

	
	// only Admin  can get all restaurent
	public List<Restaurant>getAllRestaurant();

	
	
	//  searchRestaurent
	public List<Restaurant>searchRestaurant(String keyword);
	
	
	//
	
	public Restaurant findRestaurantById(Long id) throws RestaurantException;

	public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException;
	
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws RestaurantException;

	
	// if ownner wants to close the restaurent
	public Restaurant updateRestaurantStatus(Long id)  throws RestaurantException;
}
