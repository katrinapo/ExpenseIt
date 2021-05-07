package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserDAOImpl;
import com.example.model.User;
import com.example.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	
	static UserService userServ = new UserService(new UserDAOImpl());
	
	public static String login(HttpServletRequest req)  {
		System.out.println("in user controller login");
		if(!req.getMethod().equals("POST")){
			return "html/index.html";
		}
		
		User user = userServ.getUserVerify(req.getParameter("uName"), req.getParameter("pWord"));
		

		if(user == null) {
			return "wrongcreds.change";
		} 
		
		else  {
			req.getSession().setAttribute("currentUser", user);
			if(user.getUserRoleId() == 0) {
				return "html/mgrhome.html";
			}
			return "html/home.html";
		} 
		
	}
	
	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User user = (User)req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}
	
	public static String logoutSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
        res.setContentType("text/html");  
        PrintWriter out=res.getWriter();  
          
        req.getRequestDispatcher("html/loggedout.html").include(req, res);  
          
        HttpSession session=req.getSession();  
        session.invalidate();  
          
        out.close();  
        
        return "html/loggedout.html";
		
	}

}
