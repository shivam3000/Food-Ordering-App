package com.foodorderBackend.foodbackend.Service;

import com.foodorderBackend.foodbackend.Exception.UserException;
import com.foodorderBackend.foodbackend.Model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	public User findUserByEmail(String email) throws UserException;
	
}
