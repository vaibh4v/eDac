package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendor")
public class VendorController
{
	public VendorController()
	{
		System.out.println("in ctor of " + getClass().getName());
	}
	
	@GetMapping("/details")
	public String showVendorDetails()
	{
		System.out.println("in showVendorDetails");
		
		return "/vendor/details"; //logical view name
	}
}