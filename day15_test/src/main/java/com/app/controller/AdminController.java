package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	private IUserService userService;
	
	public AdminController()
	{
		System.out.println("in ctor of AdminController");
	}
	
	@GetMapping("/list")
	public String showVendors(Model modelMap)
	{
		System.out.println("in showVendors " + modelMap);
		
		modelMap.addAttribute("vendor_list", userService.getAllVendors());
		
		return "/admin/list"; //logical view name
	}
	
	//add new request handling method for deleting vendor details
	@GetMapping("/delete")
	public String deleteVendorDetails(@RequestParam int vid, RedirectAttributes flashMap)
	{
		System.out.println("in deleteVendorDetails");
		
		flashMap.addFlashAttribute("message", userService.deleteVendorDetails(vid));
		
		return "redirect:/admin/list";
	}
	
	@GetMapping("/add")
	public String showVendorRegistrationForm(User vendor)
	{
		System.out.println("in showVendorRegistrationForm");
		
		return "/admin/add"; //logical view name
	}
	
	@PostMapping("/add")
	public String processVendorRegistrationForm(User vendor, RedirectAttributes flashMap)
	{
		System.out.println("in processVendorRegistrationForm");
		
		flashMap.addFlashAttribute("message", userService.registerVendor(vendor));
		
		return "redirect:/admin/list";
	}
	
}
