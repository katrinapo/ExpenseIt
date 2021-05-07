package com.example.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.controller.ReimbursementController;
import com.example.controller.UserController;
import com.example.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcherServlet {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException, ServletException {
		switch(req.getRequestURI()) {
		
		case "/ERS/getsessionuser.json":
			UserController.getSessionUser(req, res);
			break;
		case "/ERS/gettotalpending.json":
			ReimbursementController.displayTotalPending(req, res);
			break;
		case "/ERS/getpending.json":
			ReimbursementController.displayPendingReimbursements(req, res);
			break;
		case "/ERS/getapproved.json":
			ReimbursementController.displayApprovedReimbursements(req, res);
			break;
		case "/ERS/getdenied.json":
			ReimbursementController.displayDeniedReimbursements(req, res);
			break;
		case "/ERS/submitreimbursement.json":
			ReimbursementController.submitReimbursement(req, res);
			
			break;
		case "/ERS/approvereimbursement.json":
			ReimbursementController.approveReimbursement(req, res);
			
			break;
		case "/ERS/handlereimbursement.json":
			ReimbursementController.handleReimbursement(req, res);
			
			break;
		case "/ERS/getpendingbyemp.json":
			ReimbursementController.displayPendingReimbursementsByEmp(req, res);
			break;
		case "/ERS/getapprovedbyemp.json":
			ReimbursementController.displayApprovedReimbursementsByEmp(req, res);
			break;
		case "/ERS/getdeniedbyemp.json":
			ReimbursementController.displayDeniedReimbursementsByEmp(req, res);
			break;
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		}
	}
}
