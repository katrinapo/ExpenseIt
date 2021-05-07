package com.example.service;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.List;

import com.example.dao.ReimbDAOImpl;
import com.example.model.Reimbursement;

public class ReimbursementService {
	
	
	public final Logger log = Logger.getLogger(ReimbursementService.class);
	
	public void reimbApproved() {
		log.info("A reimbursement was approved");
	}
	
	public void reimbDenied() {
		log.info("A reimbursement was denied");
	}
	
	
	
	private ReimbDAOImpl rDao;

	public ReimbursementService(ReimbDAOImpl rDao) {
		this.rDao = rDao;
	}

	public ReimbursementService() {
		
		// TODO Auto-generated constructor stub
	}
	
	public int getTotalPending() {
		int totalPending = rDao.getTotalPending();
		return totalPending;
	}
	
	public List<Reimbursement> getPendingReimbursementsByEmployee(int employeeId) {
		List<Reimbursement> pendingEmpReimbursements = rDao.getPendingByEmployeeId(employeeId);
		return pendingEmpReimbursements;
	}
	
	
	public List<Reimbursement> getApprovedReimbursementsByEmployee(int employeeId) {
		List<Reimbursement> approvedEmpReimbursements = rDao.getApprovedByEmployeeId(employeeId);
		return approvedEmpReimbursements;
	}
	
	public List<Reimbursement> getDeniedReimbursementsByEmployee(int employeeId) {
		List<Reimbursement> deniedEmpReimbursements = rDao.getDeniedByEmployeeId(employeeId);
		return deniedEmpReimbursements;
	}
	
	
	public List<Reimbursement> getPendingReimbursements() {
		List<Reimbursement> pendingReimbursements = rDao.getPending();
		return pendingReimbursements;
		
	}
	
	public List <Reimbursement> getApprovedReimbursements(){
		List<Reimbursement> approvedReimbursements = rDao.getApproved();
		return approvedReimbursements;
	}
	
	public List <Reimbursement> getDeniedReimbursements(){
		List<Reimbursement> deniedReimbursements = rDao.getDenied();
		return deniedReimbursements;
	}


	public String insertReimbursement(BigDecimal amount, String description, int author, int type) {
		rDao.insert(amount,description,author,type);
		return "success";
		
	}
	
	public String approveReimbursement(int reimbID) {
		rDao.setApproved(reimbID);
		reimbApproved();
		return "approved!";
	}
	
	public String denyReimbursement(int reimbID) {
		rDao.setDenied(reimbID);
		reimbDenied();
		return "denied!";
	}
	
	public void setResolved(int reimbID) {
		rDao.setResolved(reimbID);
	}
	
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> allReimbursements = rDao.getAll();
		return allReimbursements;
	}
}
