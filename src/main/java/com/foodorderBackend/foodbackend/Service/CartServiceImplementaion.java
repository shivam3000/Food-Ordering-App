package com.foodorderBackend.foodbackend.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderBackend.foodbackend.Exception.CartException;
import com.foodorderBackend.foodbackend.Exception.CartItemException;
import com.foodorderBackend.foodbackend.Exception.FoodException;
import com.foodorderBackend.foodbackend.Exception.UserException;
import com.foodorderBackend.foodbackend.Model.Cart;
import com.foodorderBackend.foodbackend.Model.CartItem;
import com.foodorderBackend.foodbackend.Model.Food;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Repository.CartItemRepository;
import com.foodorderBackend.foodbackend.Repository.CartRepository;
import com.foodorderBackend.foodbackend.Repository.FoodRepository;
import com.foodorderBackend.foodbackend.Request.AddCartItemRequest;

@Service
public class CartServiceImplementaion implements  CartService  {
	
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserService userService;
	
	
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	@Autowired
	private FoodRepository menuItemRepository;

	

	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt)
			throws UserException, FoodException, CartException, CartItemException {
		// TODO Auto-generated method stub
		
		
           User user = userService.findUserProfileByJwt(jwt);
		
		Optional<Food> menuItem=menuItemRepository.findById(req.getMenuItemId());
		if(menuItem.isEmpty()) {
			throw new FoodException("Menu Item not exist with id "+req.getMenuItemId());
		}

		Cart cart = findCartByUserId(user.getId());

		for (CartItem cartItem : cart.getItems()) {
			if (cartItem.getFood().equals(menuItem.get())) {
				int newQuantity = cartItem.getQuantity() + req.getQuantity();
				return updateCartItemQuantity(cartItem.getId(),newQuantity);
			}
		}
		
		
		CartItem newCartItem = new CartItem();
		newCartItem.setFood(menuItem.get());
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setCart(cart);
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity()*menuItem.get().getPrice());
		
		CartItem savedItem=cartItemRepository.save(newCartItem);
		cart.getItems().add(savedItem);
		cartRepository.save(cart);
		
		return savedItem;
		
	}
		
	

	@Override
	public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws CartItemException {
		// TODO Auto-generated method stub
		
		
		Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
		if(cartItem.isEmpty()) {
			throw new CartItemException("cart item not exist with id "+cartItemId);
		}
		cartItem.get().setQuantity(quantity);
		cartItem.get().setTotalPrice((cartItem.get().getFood().getPrice()*quantity));
		return cartItemRepository.save(cartItem.get());
		
		
	}

	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException {
		User user = userService.findUserProfileByJwt(jwt);
		Cart cart = findCartByUserId(user.getId());
		Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
		if(cartItem.isEmpty()) {
			throw new CartItemException("cart item not exist with id "+cartItemId);
		}
		cart.getItems().remove(cartItem.get());
		return cartRepository.save(cart);
		
	}

	@Override
	public Long calculateCartTotals(Cart cart) throws UserException {
		Long total = 0L;
		for (CartItem cartItem : cart.getItems()) {
			total += cartItem.getFood().getPrice() * cartItem.getQuantity();
		}
		return total;
	
	}

	@Override
	public Cart findCartById(Long id) throws CartException {
		// TODO Auto-generated method stub
		
		Optional<Cart> cart = cartRepository.findById(id);
		if(cart.isPresent()) {
			return cart.get();
		}
		throw new CartException("Cart not found with the id "+id);
	
	}

	@Override
	public Cart findCartByUserId(Long userId) throws CartException, UserException {
	
	Optional<Cart> opt=cartRepository.findByCustomer_Id(userId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartException("cart not found");
		
	
	}

	@Override
	public Cart clearCart(Long userId) throws CartException, UserException {
		Cart cart=findCartByUserId(userId);
		cart.getItems().clear();
		return cartRepository.save(cart);
		
	}

}
