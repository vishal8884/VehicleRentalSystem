package com.vishal.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vishal.vehicle.entities.Feedback;
import com.vishal.vehicle.entities.User;
import com.vishal.vehicle.entities.Vehicle;
import com.vishal.vehicle.repository.UserRepository;
import com.vishal.vehicle.repository.feedbackRepository;

@Controller
public class AdminController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	feedbackRepository feedbackRepo;
	
	@PostMapping("/admin/regVendor") //@RequestMapping("/update/{id}") also works
	public String regVendor(@ModelAttribute("user") User user , ModelMap modelMap)
	{
		user.setRole("VENDOR");
		try {
			User savedUser=userRepo.save(user);
			modelMap.addAttribute("savedUser", savedUser);
			modelMap.addAttribute("savesuccess", "Vendor is saved successfully");
		} catch (Exception e) 
		{
			modelMap.addAttribute("error", "error while saving dublicate value entered");
		}
		
		
		return "dashboard/adminDashboard";
	}
	
	@RequestMapping("/admin/viewVendors")
	public String viewVendors(ModelMap modelMap)
	{
		List<User> allVendors = userRepo.findAll();
		List<User> vendors2 = new ArrayList<User>();
		for(User u : allVendors)
		{
			if(u.getRole().equals("VENDOR"))
				vendors2.add(u);
		}
		modelMap.addAttribute("allVendors", vendors2);
		return "dashboard/viewVendors";
	}
	
	@RequestMapping(path = "/deletevendor/{id}")
	public String deleteVendor(@PathVariable("id") int id)
	{
		try {
			userRepo.deleteById(id);
		} catch (Exception e) {
			System.out.println("id not found");
		}
		return "redirect:/admin/viewVendors";
	}
	
	@GetMapping("/editvendor/{id}")
	public String editVehicles(@PathVariable("id") Integer id,ModelMap model)
	{
		User vendor = userRepo.findById(id).orElse(new User());
		model.addAttribute("vendor", vendor);
		return "dashboard/updateVendors";
	}
	
	@RequestMapping("/updatevendor/{id}") //@PostMapping("/update/{id}") also works
	public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
	    if (result.hasErrors()) {
	    	user.setId(id);
	        return "dashboard/updateVendors";
	    }
	    user.setRole("VENDOR");    
	    userRepo.save(user);
	    return "redirect:/admin/viewVendors";
	}
	
	@RequestMapping("/admin/viewUsers")
	public String viewUsers(ModelMap modelMap)
	{
		List<User> allUsers = userRepo.findAll();
		List<User> allUsers2 = new ArrayList<User>();
		for(User u : allUsers)
		{
			if(u.getRole().equals("USER"))
				allUsers2.add(u);
		}
		modelMap.addAttribute("allUsers", allUsers2);
		
		return "dashboard/viewUsers";
	}
	
	@RequestMapping("admin/viewAllReviews")
	public String viewAllReviews(ModelMap modelMap)
	{
		List<Feedback> allReviews = feedbackRepo.findAll();
		modelMap.addAttribute("allReviews", allReviews);
		return "dashboard/viewReviews";
	}

}
