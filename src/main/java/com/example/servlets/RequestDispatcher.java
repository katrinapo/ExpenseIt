package com.example.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestDispatcher {

	public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException  {
		switch(req.getRequestURI()) {
		case "/ERS/login.change":
			System.out.println("in login.change dispatcher");
			return UserController.login(req);
		case "/ERS/logout.change":
			System.out.println("in logout.change dispatcher");
			return UserController.logoutSessionUser(req, res);
		default:
			System.out.println("in default");
			return "html/unsuccessfullogin.html";
		}
	}
}
