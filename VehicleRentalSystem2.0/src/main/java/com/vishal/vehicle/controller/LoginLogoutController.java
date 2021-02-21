package com.vishal.vehicle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginLogoutController {
	
	@RequestMapping("/admin/login")
	public String showAdminLogin()
	{
		return "loginPages/loginAdmin";
	}
	
	@RequestMapping("/admin/dashboard")
	public String adminDashboard()
	{
		return "dashboard/adminDashboard";
	}
	
	@RequestMapping("/vendor/login")
	public String vendorLogin()
	{
		return "loginPages/loginVendor";
	}
	
	
	@RequestMapping("/vendor/dashboard")
	public String VendorDashboard()
	{
		return "dashboard/vendorDashboard";
	}
	
	
	@RequestMapping("/user/login")
	public String userLogin()
	{
		return "loginPages/loginUser";
	}
	
	
	@RequestMapping("/user/dashboard")
	public String userDashboard()
	{
		return "dashboard/userDashboard";
	}
	
	

}
