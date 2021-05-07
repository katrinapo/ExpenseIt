package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.model.User;


public class UserDAOImpl implements UserDAO {
	
	private static final String URL = "jdbc:postgresql://database-1.ces41klochst.us-east-2.rds.amazonaws.com:5432/reimdb";
	private static final String USERNAME = "reimbuser";
	private static final String PASSWORD = "Passw0rd";
	
	
	

	@Override
	public List<User> getAll()  {
		List<User> userList = new ArrayList<User>();
		// TODO Auto-generated method stub;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			
			String sql = "select * from ers_users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userList.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	
	public User getUserByUserName(String username) {

		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_users where ers_users.ers_username=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			User user = new User();
			while(rs.next()) {
				user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
			} return user;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	private List<User> fakeUserList;
	
	public UserDAOImpl() {
		fakeUserList = new ArrayList<User>(Arrays.asList
				(new User("cond","livonia",0), new User("teresitapo","brantwood",1), new User("rosedg", "livonia",0)));
	}
	
	public List<User> getAllUsers() {
		return fakeUserList;
	}
	
	public User getUserByUserName(String username) {
		for(User user: fakeUserList) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		} return null;
	}
	*/

}
