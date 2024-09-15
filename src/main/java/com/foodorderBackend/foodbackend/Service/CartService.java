package com.foodorderBackend.foodbackend.Service;

import com.foodorderBackend.foodbackend.Exception.CartException;
import com.foodorderBackend.foodbackend.Exception.CartItemException;
import com.foodorderBackend.foodbackend.Exception.FoodException;
import com.foodorderBackend.foodbackend.Exception.UserException;
import com.foodorderBackend.foodbackend.Model.Cart;
import com.foodorderBackend.foodbackend.Model.CartItem;
import com.foodorderBackend.foodbackend.Request.AddCartItemRequest;

public interface CartService {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	
	public Cart clearCart(Long userId) throws CartException, UserException;
	
}
