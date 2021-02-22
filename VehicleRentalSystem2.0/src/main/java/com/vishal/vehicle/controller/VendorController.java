package com.vishal.vehicle.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vishal.vehicle.entities.User;
import com.vishal.vehicle.entities.Vehicle;
import com.vishal.vehicle.repository.UserRepository;
import com.vishal.vehicle.repository.VehicleRepository;

@Controller
public class VendorController {
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	String current_name="";
	User current_user = new User();
	
	@RequestMapping("/vendor/addVehicle")
	public String addVehicle(@ModelAttribute("vehicle") Vehicle vehicle , Principal principal) //principal is used to get curent user
	{
		 current_name = principal.getName();
		 current_user = userRepo.findByName(current_name);
		 vehicle.setVendor(current_user);
		 vehicleRepo.save(vehicle);
		
		
		return "dashboard/vendorDashboard";
	}
	
	@RequestMapping("/vendor/viewVehicles")
	public String viewVehicles(ModelMap modelMap,Principal principal)
	{
		 current_name = principal.getName();
		 current_user = userRepo.findByName(current_name);
		int currentId = current_user.getId();
		List<Vehicle> allVehicles = vehicleRepo.findAll();
		List<Vehicle> viewVehicles = new ArrayList<Vehicle>();
		for(Vehicle v : allVehicles)
		{
			if(v.getVendor().getId()==currentId && v.isAvailable()==true)
				viewVehicles.add(v);
		}
		modelMap.addAttribute("allVehicles", viewVehicles);
		return "dashboard/viewVehicles";
	}
	
	@GetMapping("/edit/{id}")
	public String editVehicles(@PathVariable("id") Integer id,ModelMap model)
	{
		Vehicle vehicle = vehicleRepo.findById(id).orElse(new Vehicle());
		model.addAttribute("vehicle", vehicle);
		return "dashboard/updateVehicles";
	}
	
	@RequestMapping("/update/{id}") //@PostMapping("/update/{id}") also works
	public String updateUser(@PathVariable("id") int id, @Valid Vehicle vehicle, BindingResult result, ModelMap model) {
	    if (result.hasErrors()) {
	    	vehicle.setId(id);
	        return "dashboard/updateVehicles";
	    }
		 vehicle.setVendor(current_user);
		 
	    vehicleRepo.save(vehicle);
	    return "redirect:/vendor/viewVehicles";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteVehicles(@PathVariable("id") int id)
	{
		try {
			vehicleRepo.deleteById(id);
		} catch (Exception e) {
			System.out.println("id not found");
		}
		return "redirect:/vendor/viewVehicles";
	}
	

}












//Vehicle vehicle = vehicleRepo.findById(id).orElse(new Vehicle());
//model.addAttribute("vehicle", vehicle);
//if (result.hasErrors()) {
//    vehicle.setId(id);
//    return "update-user";
//}
////if(id!=null)
////{
////	Vehicle vehicle = vehicleRepo.findById(id);
////    model.addAttribute("vehicle", vehicle);
////}
////else
////{
////	model.addAttribute("vehicle", new Vehicle());
////}
//return "dashboard/updateVehicles";