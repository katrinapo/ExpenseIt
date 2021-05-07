package com.example;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.example.dao.ReimbDAOImpl;
import com.example.dao.UserDAOImpl;
import com.example.model.Reimbursement;
import com.example.service.ReimbursementService;
import com.example.service.UserService;

public class MainDriver {

	public static void main(String[] args) {
		UserDAOImpl uDao = new UserDAOImpl();
		// TODO Auto-generated method stub
		UserService uServ = new UserService(uDao);
		
		ReimbDAOImpl rDao = new ReimbDAOImpl();
		ReimbursementService rServ = new ReimbursementService(rDao);
		
		System.out.println(rServ.getPendingReimbursementsByEmployee(3));
	}

}
