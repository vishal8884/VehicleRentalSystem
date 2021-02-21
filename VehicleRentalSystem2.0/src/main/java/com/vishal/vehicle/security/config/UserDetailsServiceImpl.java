package com.vishal.vehicle.security.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vishal.vehicle.entities.User;
import com.vishal.vehicle.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username);
		
		if (user==null) {
			throw new UsernameNotFoundException("Could not found user !!");
		}
		
		UserDetailsImpl userDetailsImpl  = new UserDetailsImpl(user);
		System.out.println(userDetailsImpl.getAuthorities()+"  list of authorities");
			return userDetailsImpl;
	}

}
