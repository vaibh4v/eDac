package com.app.dao;

import java.util.List;

import com.app.pojos.User;

public interface IUserDao
{
	User validateUser(String email, String password);
	
	List<User> getAllVendors();
	
	String deleteVendorDetails(User vendor);
	
	User findByUserId(int userId);
	
	String registerVendor(User vendor);
}