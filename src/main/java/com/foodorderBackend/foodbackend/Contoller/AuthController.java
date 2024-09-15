package com.foodorderBackend.foodbackend.Contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderBackend.foodbackend.Config.JwtProvider;
import com.foodorderBackend.foodbackend.Domain.USER_ROLE;
import com.foodorderBackend.foodbackend.Model.Cart;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Repository.CartRepository;
import com.foodorderBackend.foodbackend.Repository.UserRepository;
import com.foodorderBackend.foodbackend.Request.LoginRequest;
import com.foodorderBackend.foodbackend.Response.AuthResponse;
import com.foodorderBackend.foodbackend.Service.CustomUserServiceimplementation;


@RequestMapping("/auth")
@RestController
public class AuthController {
	
     @Autowired
	  private UserRepository userRepository;
     
     @Autowired
	  private PasswordEncoder passwordEncoder;
     
     @Autowired
	 private JwtProvider jwtProvider;
     
     @Autowired
     private CustomUserServiceimplementation customUserDetails;
    
     
     @Autowired
     private CartRepository   cartRepository ; 
     
     
     @PostMapping("/signup")
 	  public ResponseEntity<AuthResponse> createUserHandler( @RequestBody User user) throws UsernameNotFoundException {
 
 		String email = user.getEmail();
 		String password = user.getPassword();
 		String fullName = user.getFullName();
 		USER_ROLE role=user.getRole();

 		User isEmailExist = userRepository.findByEmail(email);

 		if (isEmailExist!=null) {

 			throw new UsernameNotFoundException("Email Is Already Used With Another Account");
 		}

 		// Create new user
 		User createdUser = new User();
 		createdUser.setEmail(email);
 		createdUser.setFullName(fullName);
 		createdUser.setPassword(passwordEncoder.encode(password));
 		createdUser.setRole(role);

 		User savedUser = userRepository.save(createdUser);
 		// Create A new Cart for USer
 		Cart cart = new Cart();
 		cart.setCustomer(savedUser);
 		Cart savedCart = cartRepository.save(cart);
// 		savedUser.setCart(savedCart);

 		List<GrantedAuthority> authorities=new ArrayList<>();

 		authorities.add(new SimpleGrantedAuthority(role.toString())); 
 		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password,authorities);
 		SecurityContextHolder.getContext().setAuthentication(authentication);

 		String token = jwtProvider.generateToken(authentication);

 		AuthResponse authResponse = new AuthResponse();
 		authResponse.setJwt(token);
 		authResponse.setMessage("Register Success");
 		authResponse.setRole(savedUser.getRole());

 		return new ResponseEntity<>(authResponse, HttpStatus.OK);

 	}
     
     
 	@PostMapping("/signin")
 	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {

 		String username = loginRequest.getEmail();
 		String password = loginRequest.getPassword();

 		System.out.println(username + " ----- " + password);

 		
 		// Set authenthictaion TOKEN
 		Authentication authentication = authenticate(username, password);
 		SecurityContextHolder.getContext().setAuthentication(authentication);

 		
 		
 		// Generate Token
 		String token = jwtProvider.generateToken(authentication);
 		AuthResponse authResponse = new AuthResponse();

 		authResponse.setMessage("Login Success");
 		authResponse.setJwt(token);
 		
 		
 		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


		String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();


 		authResponse.setRole(USER_ROLE.valueOf(roleName));

 		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
 	}
 	
 	
 	

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);

	//	System.out.println("sign in userDetails - " + userDetails);

		if (userDetails == null) {
			System.out.println("sign in userDetails - null " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match " + userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
 

}
