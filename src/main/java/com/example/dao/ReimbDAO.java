package com.example.dao;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.example.model.Reimbursement;

public interface ReimbDAO {
	
	//void insertReimbursement(); needs arguments
	
//	List<Reimbursement> getAllByUserName(String userName);
//	List<Reimbursement> getPendingByUserName(String userName);
//	List<Reimbursement> getApprovedByUserName(String userName);
//	List<Reimbursement> getDeniedByUserName(String userName);
	
//	List<Reimbursement> getAll();
	List<Reimbursement> getPending();
	List<Reimbursement> getApproved();
	List<Reimbursement> getDenied();
	
	List<Reimbursement> getPendingByEmployeeId(int employeeId);
	List<Reimbursement> getApprovedByEmployeeId(int employeeId);
	List<Reimbursement> getDeniedByEmployeeId(int employeeId);
	
	int getTotalPending();
	
	void insert(BigDecimal amount, String description, int author, int type);
	void setApproved(int reimbID);
	void setDenied(int reimbID);
	void setResolved(int reimbID);
	List<Reimbursement> getAll();
	
	
}
