package com.foodorderBackend.foodbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderBackend.foodbackend.Config.JwtProvider;
import com.foodorderBackend.foodbackend.Exception.UserException;
import com.foodorderBackend.foodbackend.Model.User;
import com.foodorderBackend.foodbackend.Repository.UserRepository;

@Service
public class UserServiceimpl   implements  UserService  {
	
	@Autowired 
    private UserRepository  userRepository  ; 

	@Autowired 
	private JwtProvider jwtProvider ; 
	

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
//		System.out.println("email user "+user.get().getEmail());
		return user;
	}
	
		
	

	@Override
	public User findUserByEmail(String email) throws UserException {
		User user=userRepository.findByEmail(email);
		if(user!=null) {
			return user;
		}
		
		throw new UserException("user not exist with username "+email);
	}

	
	
	
	 

}
