package com.foodorderBackend.foodbackend.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Service.UserService;
import com.foodorderBackend.foodbackend.Exception.UserException;

@RestController
@RequestMapping("/api/users")
public class UserController {

	 @Autowired 
	 UserService userService ; 
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);
		user.setPassword(null);

		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
}
