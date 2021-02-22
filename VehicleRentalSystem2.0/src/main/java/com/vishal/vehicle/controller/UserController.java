package com.vishal.vehicle.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.vehicle.entities.User;
import com.vishal.vehicle.entities.Vehicle;
import com.vishal.vehicle.repository.UserRepository;
import com.vishal.vehicle.repository.VehicleRepository;
import com.vishal.vehicle.service.ViewSelectedVehiclesServiceImpl;
import com.vishal.vehicle.service.viewSelectedVehicleServiceI;

@Controller
public class UserController {
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private viewSelectedVehicleServiceI viewSelectedVehiclesService;
	
	
	
	private Vehicle selectedVehicle = new Vehicle();

	@RequestMapping("/user/checkAvailability")
	public String checkAvail(ModelMap modelMap)
	{
		List<Vehicle> allVehicles = vehicleRepo.findAll();
		Set<Vehicle> availableVehicles = new HashSet<Vehicle>();
		for(Vehicle v : allVehicles)
		{
			if(v.isAvailable()==true)
				availableVehicles.add(v);
		}
		modelMap.addAttribute("allVehicles", availableVehicles);
		return "dashboard/booking/vehicleAvailability";
	}
	
	@RequestMapping("/user/viewSelctedVehicles")
	public String viewSelectedVehicles(@RequestParam("id") int id , ModelMap modelMap ,Principal principal)
	{
		selectedVehicle = vehicleRepo.findById(id).orElse(new Vehicle());
		if(selectedVehicle!=null) {
			selectedVehicle.setAvailable(false);
		modelMap.addAttribute("selectedVehicles",selectedVehicle);
		}
		
        Set<Vehicle> selectedVehicles = new HashSet<Vehicle>();
        selectedVehicles.add(selectedVehicle);
		String userName =  principal.getName();  //principal is used to get current logged in user
	 	User user = userRepo.findByName(userName);
		
		
		if(user!=null) 
		{	
		user.setVehicle(selectedVehicles);  //this maps value of user_id and vehicle_id
		userRepo.save(user);
		}
		
		return "dashboard/booking/viewSelectedVehicles";
	}
	
	
	
	
	
	@RequestMapping("/user/bookVehicle")
	public String bookVehicle(ModelMap modelMap)
	{
//		System.out.println("Rating is :: "+rating);
		modelMap.addAttribute("selectedVehicles",selectedVehicle);
		return "dashboard/booking/bookingSuccessful";
	}
	
	@RequestMapping("/user/bookVehicleReview")
	public String thanks(@RequestParam("rating") int rating)
	{
		System.out.println("rating ::"+rating);
		return "dashboard/booking/bookingSuccessfulReview";
	}
	
}
























//
//public int getCurrentUserId() {
//	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	String username=" ";
//	if (principal instanceof UserDetails) {
//	username =((UserDetails) principal).getUsername();
//	} else {
//	username = principal.toString();
//	}
//	
//	User user3 = userRepo.findByName(username);
//	int current_user_id = user3.getId();
//	System.out.println(username+"  :: current user name     "+current_user_id+" :: current user id");
//	return current_user_id;
//}
//
//public Set<Vehicle> selectedVehiclesSet(String[] ids) {
//	Set<Vehicle> selectedVehicles = new HashSet<Vehicle>();
//	for(String s : ids) {
//	    int id = Integer.valueOf(s);
//	    Vehicle vehicle = vehicleRepo.findById(id).orElse(new Vehicle());
//	    if(vehicle!=null)
//	    selectedVehicles.add(vehicle);
//	}
//	return selectedVehicles;
//}




//
//
//List<User> allUsers = userRepo.findAll();
//List<User> allUsersIdWithRoleUser = new ArrayList<User>();
//
//System.out.println("all users before::"+allUsers);
//for(User u : allUsers)
//{
//	if(u.getRole().equals("USER") && u!=null)
//	{
//		allUsersIdWithRoleUser.add(u);
//	}
//}
//System.out.println("all users after::"+allUsersIdWithRoleUser);
//
//Set<Vehicle> bookedvehicle = new HashSet<Vehicle>();
//
//for(User u : allUsersIdWithRoleUser)
//{
//	Set<Vehicle> vehicle = u.getVehicle();
//	  for(Vehicle v : vehicle)
//	  {
//		  bookedvehicle.add(v);
//	  }
//}
//
//allVehicles.removeAll(bookedvehicle);















//THIS IS FOR CHECK BOX

//@RequestMapping("/user/viewSelctedVehicles")
//public String viewSelectedVehicles(@RequestParam("id") String[] ids , ModelMap modelMap ,Principal principal)
//{
//	selectedVehicles = viewSelectedVehiclesService.selectedVehiclesSet(ids);
//	
//	modelMap.addAttribute("selectedVehicles",selectedVehicles);
//	
//	String userName =  principal.getName();  //principal is used to get current logged in user
// 	User user_for_id = userRepo.findByName(userName);
//	
//	
//	User user = userRepo.findById(user_for_id.getId()).orElse(new User());
//	if(user!=null) 
//	{	
//	user.setVehicle(selectedVehicles);  //this maps value of user_id and vehicle_id
//	userRepo.save(user);
//	}
//	return "dashboard/booking/viewSelectedVehicles";
//}