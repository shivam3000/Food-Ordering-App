package com.foodorderBackend.foodbackend.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderBackend.foodbackend.Dto.RestaurantDto;
import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Model.Address;
import com.foodorderBackend.foodbackend.Model.Restaurant;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Repository.AddressRepository;
import com.foodorderBackend.foodbackend.Repository.RestaurantRepository;
import com.foodorderBackend.foodbackend.Repository.UserRepository;
import com.foodorderBackend.foodbackend.Request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImplementation     implements RestaurantService
{

	@Autowired
   private 	AddressRepository  addressRepository  ; 
	
	
	@Autowired
    private RestaurantRepository  restaurantRepository  ; 
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		// TODO Auto-generated method stub
		
		
		Address address=new Address();
		address.setCity(req.getAddress().getCity());
		address.setCountry(req.getAddress().getCountry());
		address.setFullName(req.getAddress().getFullName());
		address.setPostalCode(req.getAddress().getPostalCode());
		address.setState(req.getAddress().getState());
		address.setStreetAddress(req.getAddress().getStreetAddress());
		Address savedAddress = addressRepository.save(address);
		
		Restaurant restaurant = new Restaurant();
		
		restaurant.setAddress(savedAddress);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(req.getRegistrationDate());
		restaurant.setOwner(user);
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);

		return savedRestaurant;
		
	
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
			throws RestaurantException {
		
		
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant.getCuisineType() != null) {
			restaurant.setCuisineType(updatedRestaurant.getCuisineType());
		}
		if (restaurant.getDescription() != null) {
			restaurant.setDescription(updatedRestaurant.getDescription());
		}
		return restaurantRepository.save(restaurant);
	
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws RestaurantException {
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant != null) {
			restaurantRepository.delete(restaurant);
			return;
		}
		throw new RestaurantException("Restaurant with id " + restaurantId + " Not found");
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	} 
		
		

	@Override
	public Restaurant findRestaurantById(Long id) throws RestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		if (restaurant.isPresent()) {
			return restaurant.get();
		} else {
			throw new RestaurantException("Restaurant with id " + id + "not found");
		}
	}

	@Override
	public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
		Restaurant restaurants=restaurantRepository.findByOwnerId(userId);
		return restaurants;
	}
	

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException {
        Restaurant restaurant=findRestaurantById(restaurantId);
		RestaurantDto dto=new RestaurantDto();
		dto.setTitle(restaurant.getName());
		dto.setImages(restaurant.getImages());
		dto.setId(restaurant.getId());
		dto.setDescription(restaurant.getDescription());
		
		boolean isFavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		for (RestaurantDto favorite : favorites) {
			if (favorite.getId().equals(restaurantId)) {
				isFavorited = true;
				break;
			}
		}

		if (isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		} else {
			favorites.add(dto);
		}
		
		User updatedUser = userRepository.save(user);
		return dto;

	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
		
		
	}

}
