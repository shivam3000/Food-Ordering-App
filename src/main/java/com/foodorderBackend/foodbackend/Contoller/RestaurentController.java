package com.foodorderBackend.foodbackend.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderBackend.foodbackend.Dto.RestaurantDto;
import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.Restaurant;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Service.RestaurantService;
import com.foodorderBackend.foodbackend.Service.UserService;
import com.foodorderBackend.foodbackend.Exception.UserException;




@RestController
@RequestMapping("/api/restaurants")
public class RestaurentController {
	
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;


	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> findRestaurantByName(
			@RequestParam String keyword) {
		List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
		return ResponseEntity.ok(restaurant);
	}


	@GetMapping()
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		List<Restaurant> restaurants = restaurantService.getAllRestaurant();
		return ResponseEntity.ok(restaurants);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(
			@PathVariable Long id) throws RestaurantException {

			Restaurant restaurant = restaurantService.findRestaurantById(id);
			return ResponseEntity.ok(restaurant);

	}
	
	@PutMapping("/{id}/add-favorites")
	public ResponseEntity<RestaurantDto> addToFavorite(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws RestaurantException, UserException {
		
		
			User user = userService.findUserProfileByJwt(jwt);
			RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
			return ResponseEntity.ok(restaurant);
	}
	

}
