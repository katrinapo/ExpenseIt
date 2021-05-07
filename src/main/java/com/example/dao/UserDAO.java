package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface UserDAO {

	List<User> getAll() throws ClassNotFoundException;
	
}
