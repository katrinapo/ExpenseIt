package com.example.service;

import java.util.List;

import com.example.dao.UserDAOImpl;
import com.example.model.User;

public class UserService {

	private UserDAOImpl uDao;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public UserService(UserDAOImpl uDao) {
		super();
		this.uDao = uDao;
	}
	
	public User getUserVerify(String username, String password) {
		
		User user = uDao.getUserByUserName(username);
		if(user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getAllUsers(){
		List<User> uList = uDao.getAll();
		return uList;
		
	}
	
}
