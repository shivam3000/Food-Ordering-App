package com.foodorderBackend.foodbackend.Service;

import java.util.List;

import com.foodorderBackend.foodbackend.Exception.CartException;
import com.foodorderBackend.foodbackend.Exception.OrderException;
import com.foodorderBackend.foodbackend.Exception.RestaurantException;
import com.foodorderBackend.foodbackend.Exception.UserException;

import com.foodorderBackend.foodbackend.Model.Order;
import com.foodorderBackend.foodbackend.Model.PaymentResponse;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Request.CreateOrderRequest;

public interface OrderService {
	
 public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException ; 
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
	 
	 public void cancelOrder(Long orderId) throws OrderException;
	 
	 public List<Order> getUserOrders(Long userId) throws OrderException;
	 
	 public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;
	 

	 

}
