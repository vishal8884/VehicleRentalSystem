package com.vishal.vehicle.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vishal.vehicle.entities.User;
import com.vishal.vehicle.entities.Vehicle;
import com.vishal.vehicle.repository.UserRepository;
import com.vishal.vehicle.repository.VehicleRepository;

@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
	
	
	@RequestMapping("/")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping("/showreg")
	public String showReg()
	{
		
		return "RegisterNewUser";
	}
	
	@PostMapping("/reguser") //@RequestMapping("/update/{id}") also works
	public String regUser(@ModelAttribute("user") User user , ModelMap modelMap)
	{
//		user.setPassword(encoder.encode(user.getPassword()));
		User savedUser=userRepo.save(user);
		modelMap.addAttribute("savedUser", savedUser);
		return "loginPages/loginUser";
	}
	
	@RequestMapping("/showUserLogin")
	public String showUserLogin()
	{
		return "loginPages/loginUser";
	}
	
	@RequestMapping("/showAdminLogin")
	public String showAdminLogin()
	{
		return "loginPages/loginAdmin";
	}
	
	@RequestMapping("/showVendorLogin")
	public String showVendorLogin()
	{
		return "loginPages/loginVendor";
	}
	
	@RequestMapping("/403")
	public String accessDeniedPage()
	{
		return "exceptions/403";
	}

}
















//Vehicle v = new Vehicle();
//v.setName("maruthi");
//v.setType("car");
//v.setCost(100);
//Vehicle v2 = new Vehicle();
//v2.setName("maruthie");
//v2.setType("bus");
//v2.setCost(1000);
//Vehicle v3 = new Vehicle();
//v3.setName("maruthi3");
//v3.setType("car");
//v3.setCost(100);
//Vehicle v4 = new Vehicle();
//v4.setName("maruthie4");
//v4.setType("bus");
//v4.setCost(1000);
//
//
//Set<Vehicle> lv= new HashSet<Vehicle>();
//lv.add(v);
//lv.add(v2);
//
//Set<Vehicle> lv2= new HashSet<Vehicle>();
//lv2.add(v3);
//lv2.add(v4);
//
//lv2.add(v);
//
//User u1 = new User();
//u1.setEmail("vis3@gmail.com");
//u1.setName("vis3");
//u1.setPassword("abcd");
//u1.setRole("USER");
//u1.setVehicle(lv);
//
//User u2 = new User();
//u2.setEmail("vis4@gmail.com");
//u2.setName("vis4");
//u2.setPassword("abcd");
//u2.setRole("USER");
//u2.setVehicle(lv2);
//
//userRepo.save(u1);
//userRepo.save(u2);
//return "home";