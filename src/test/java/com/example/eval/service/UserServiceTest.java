package com.example.eval.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.UserDAOImpl;
import com.example.model.User;
import com.example.service.UserService;

public class UserServiceTest {
	
	@Mock
	private UserDAOImpl fakeDao;
	private UserService uServ;
	
	private User user;
	private User anotherUser;



	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		uServ = new UserService(fakeDao);
		anotherUser = new User();
		user = new User("cond","livonia");
		
		when(fakeDao.getUserByUserName("cond")).thenReturn(user);
		when(fakeDao.getUserByUserName("none")).thenReturn(anotherUser);
		
		when(fakeDao.getAll()).thenReturn(new ArrayList<User>(Arrays.asList(user)));
		}
	
	@Test
	public void getUserByIdSuccess() {
		assertEquals("cond",uServ.getUserVerify("cond", "livonia").getUsername(), "success");
	}
	
}