package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service //to tell SC that this class contains BL
@Transactional
public class UserServiceImpl implements IUserService
{
	//dependency
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User validateUser(String email, String password)
	{
		System.out.println("in service : " + email + " " + password);
		
		//invoking Dao's method
		return userDao.validateUser(email, password);
	}

	@Override
	public List<User> getAllVendors()
	{
		return userDao.getAllVendors();
	}

	@Override
	public String deleteVendorDetails(int vendorId)
	{
		User vendor = userDao.findByUserId(vendorId);
		
		if(vendor != null)
		{
			return userDao.deleteVendorDetails(vendor);
		}
		
		return "Vendor deletion failed!";
	}

	@Override
	public String registerVendor(User vendor)
	{
		//here 'vendor' is Transient
		
		vendor.setUserRole(Role.VENDOR);
		
		return userDao.registerVendor(vendor);
	}
	
}