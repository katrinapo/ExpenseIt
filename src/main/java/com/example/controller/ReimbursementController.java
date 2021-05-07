package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ReimbDAOImpl;
import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.service.ReimbursementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementController {
	
	
		public static void displayTotalPending(HttpServletRequest req, HttpServletResponse res) throws IOException {
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			int totalPending = rServ.getTotalPending();
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(totalPending);
			
			res.setContentType("application/json");
			pw.write(response);
			
		}

		public static void displayPendingReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			List<Reimbursement> reimbList = rServ.getPendingReimbursements();
			
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(reimbList);
			
			res.setContentType("application/json");
			pw.write(response);

		}
		
		public static void displayApprovedReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			List<Reimbursement> apprList = rServ.getApprovedReimbursements();
			
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(apprList);
			
			res.setContentType("application/json");
			pw.write(response);

		}
		
		public static void displayDeniedReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			List<Reimbursement> apprList = rServ.getDeniedReimbursements();
			
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(apprList);
			
			res.setContentType("application/json");
			pw.write(response);

		}
		
		
		
		
		
		
		//display emp reimbursements pending
		public static void displayPendingReimbursementsByEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {
			User user = (User)req.getSession().getAttribute("currentUser");
			int userId = user.getUserid();
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			List<Reimbursement> reimbList = rServ.getPendingReimbursementsByEmployee(userId);
			
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(reimbList);
			
			res.setContentType("application/json");
			pw.write(response);

		}
		
		
		
		//display emp reimbursements approved
		public static void displayApprovedReimbursementsByEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {
			User user = (User)req.getSession().getAttribute("currentUser");
			int userId = user.getUserid();
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			List<Reimbursement> reimbList = rServ.getApprovedReimbursementsByEmployee(userId);
			
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(reimbList);
			
			res.setContentType("application/json");
			pw.write(response);

		}
		
		//display emp reimbursements denied
		public static void displayDeniedReimbursementsByEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {
			User user = (User)req.getSession().getAttribute("currentUser");
			int userId = user.getUserid();
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			List<Reimbursement> reimbList = rServ.getDeniedReimbursementsByEmployee(userId);
			
			res.setStatus(200);

			PrintWriter pw = res.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			String response = mapper.writeValueAsString(reimbList);
			
			res.setContentType("application/json");
			pw.write(response);

		}

		public static void submitReimbursement(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
			User user = (User)req.getSession().getAttribute("currentUser");
			int userId = user.getUserid();
			String amount = req.getParameter("amount");
			BigDecimal convertedAmount = new BigDecimal(amount);
			String description = req.getParameter("description");
			String stringType = req.getParameter("type");
			int type;
			/*if (stringType == "Parking") {
				type = 001;
			} 
			else if (stringType == "Flights") {
				type = 002;
			}
			else if (stringType == "Materials") {
				type = 003;
			}
			else if (stringType == "Certs") {
				type = 004;
			}
			else {
				type = 005;
			}*/
			switch(stringType) {
			case "Parking":
			type = 001;
			break;
			case "Flight":
			type = 002;
			break;
			case "Materials":
			type = 003;
			break;
			case "Certs":
			type = 004;
			break;
			case "Miscellaneous":
			type = 005;
			break;
			default:
				type = 005;
			}
			
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			
			
			if(rServ.insertReimbursement(convertedAmount,description,user.getUserid(),type) == "success") {
	 
				req.getRequestDispatcher("html/home.html").forward(req, res);
			} else {
				System.out.println("Failure!");
			}
			
		}
		
		public static void approveReimbursement(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
			User user = (User)req.getSession().getAttribute("currentUser");
			int resolverId = user.getUserid();
			int reimbID = Integer.parseInt(req.getParameter("employeeId"));
			
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			rServ.approveReimbursement(reimbID);
			req.getRequestDispatcher("html/mgrhome.html").forward(req, res);
			
		}
		
		public static void handleReimbursement(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
			User user = (User)req.getSession().getAttribute("currentUser");
			int resolverId = user.getUserid();
			int reimbID = Integer.parseInt(req.getParameter("employeeId"));
			
			String status = req.getParameter("approve");
			String status2 = req.getParameter("deny");
			
			ReimbursementService rServ = new ReimbursementService(new ReimbDAOImpl());
			if(status != null) {
				rServ.approveReimbursement(reimbID);
				rServ.setResolved(reimbID);
			} else {
				rServ.denyReimbursement(reimbID);
				rServ.setResolved(reimbID);
			}
			req.getRequestDispatcher("html/mgrhome.html").forward(req, res);
		}
		
		
		
}
