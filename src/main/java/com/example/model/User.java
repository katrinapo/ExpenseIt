package com.example.model;

public class User {

	private int userid;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int userRoleId;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password) {
		// TODO Auto-generated constructor stub
		
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, int userRoleId) {
		// TODO Auto-generated constructor stub
		
		this.username = username;
		this.password = password;
		this.userRoleId = userRoleId;
	}

	public User(int userid, String username, String password, String firstName, String lastName, String email,
			int userRoleId) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleId = userRoleId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "User [\nuserid=" + userid + ", \nusername=" + username + ", \npassword=" + password + ", \nfirstName="
				+ firstName + ", \nlastName=" + lastName + ", \nemail=" + email + ", \nuserRoleId=" + userRoleId + "]";
	}
	
	
	
	
}
