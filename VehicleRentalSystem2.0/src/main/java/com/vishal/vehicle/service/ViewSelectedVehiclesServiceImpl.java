package com.vishal.vehicle.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vishal.vehicle.entities.User;
import com.vishal.vehicle.entities.Vehicle;
import com.vishal.vehicle.repository.UserRepository;
import com.vishal.vehicle.repository.VehicleRepository;

@Service
public class ViewSelectedVehiclesServiceImpl implements viewSelectedVehicleServiceI {

	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	UserRepository userRepo;
	

	public Set<Vehicle> selectedVehiclesSet(String[] ids) {
		Set<Vehicle> selectedVehicles = new HashSet<Vehicle>();
		for(String s : ids) {
		    int id = Integer.valueOf(s);
		    Vehicle vehicle = vehicleRepo.findById(id).orElse(new Vehicle());
		    if(vehicle!=null)
		    {
		    	vehicle.setAvailable(false);
		    selectedVehicles.add(vehicle);
		    }
		}
		return selectedVehicles;
	}
}




























//public int getCurrentUserId() {
//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//String username=" ";
//if (principal instanceof UserDetails) {
//username =((UserDetails) principal).getUsername();
//} else {
//username = principal.toString();
//}
//
//
//User user3 = userRepo.findByName(username);
//int current_user_id = user3.getId();
//
//System.out.println(username+"  :: current user name     "+current_user_id+" :: current user id");
//return current_user_id;
//}