package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController
{
	//dependency
	@Autowired
	private IUserService userService;
	
	public UserController()
	{
		System.out.println("in ctor of " + getClass().getName());
	}
	
	@GetMapping("/login")
	public String showLoginForm()
	{
		System.out.println("in showLoginForm");
		return "/user/login"; //logical view name
	}
	
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email, @RequestParam(name = "pass") String pwd,
								   Model modelMap, HttpSession session)
	{
		System.out.println("in processLoginForm " + email + " " + pwd);
		
		//invoke service layer method
		try
		{
			User validatedUser = userService.validateUser(email, pwd);
			
			//valid login branch
			session.setAttribute("user_details", validatedUser);
			
			session.setAttribute("message", "login successful under the role of " + validatedUser.getUserRole());
			
			if(validatedUser.getUserRole().equals(Role.ADMIN))
			{
				return "redirect:/admin/list";
			}
			else
			{
				return "redirect:/vendor/details";
			}
		}
		catch(RuntimeException e)
		{
			//invalid login branch
			e.printStackTrace();
			
			modelMap.addAttribute("message", "Invalid Login! Please Retry.");
			
			return "/user/login";
		}
	}
	
	//logout method
	@GetMapping("/logout")
	public String userLogout(HttpSession session, Model modelMap, HttpServletRequest req, HttpServletResponse resp)
	{
		System.out.println("in userLogout");
		
		//access user details from session scope and add them in the model map(request scope)
		modelMap.addAttribute("details", session.getAttribute("user_details"));
		
		// discard/invalidate the session
		session.invalidate();
		
		resp.setHeader("refresh", "5;url=" + req.getContextPath());
		
		return "/user/logout";
	}
	
}