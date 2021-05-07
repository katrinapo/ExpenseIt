package com.example.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.model.Reimbursement;

public class ReimbDAOImpl implements ReimbDAO {
	
	private static final String URL = "jdbc:postgresql://database-1.ces41klochst.us-east-2.rds.amazonaws.com:5432/reimdb";
	private static final String USERNAME = "reimbuser";
	private static final String PASSWORD = "Passw0rd";
	
	@Override
	public int getTotalPending() {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql ="select count(*) from ers_reimbursement where reimb_status_id = 1; ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int totalPending = 0;
			while(rs.next()) {
				totalPending = rs.getInt(1);
				
			} return totalPending;
		}catch(SQLException e) {
			e.printStackTrace();
		}return 0;
		
	}
	

	@Override
	public List<Reimbursement> getPendingByEmployeeId(int employeeId) {
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement where reimb_status_id=1 and reimb_author=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pendingReimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pendingReimbursements;
	}
	
	@Override
	public List<Reimbursement> getApprovedByEmployeeId(int employeeId) {
		List<Reimbursement> approvedReimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement where reimb_status_id=2 and reimb_author=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				approvedReimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return approvedReimbursements;
	}

	@Override
	public List<Reimbursement> getDeniedByEmployeeId(int employeeId) {
		List<Reimbursement> deniedReimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement where reimb_status_id=3 and reimb_author=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				deniedReimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return deniedReimbursements;
	}

	@Override
	public List<Reimbursement> getPending() {
		
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement where reimb_status_id=1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pendingReimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pendingReimbursements;
	
	}
	
	@Override
	public List<Reimbursement> getAll() {
		
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	
	}
	
	
	@Override
	public List<Reimbursement> getApproved() {
		// TODO Auto-generated method stub
		
		List<Reimbursement> approvedReimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement where reimb_status_id=2";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				approvedReimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return approvedReimbursements;
	}
	
	@Override
	public List<Reimbursement> getDenied() {
		// TODO Auto-generated method stub
		List<Reimbursement> deniedReimbursements = new ArrayList<Reimbursement>();
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection con =  DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from ers_reimbursement where reimb_status_id=3";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				deniedReimbursements.add(new Reimbursement(rs.getInt(1),rs.getBigDecimal(2),
						rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return deniedReimbursements;
	}
	
	
	  @Override 
	  public void insert(BigDecimal amount, String description, int author, int type) { // TODO Auto-generated method stub
		  try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			  String sql="insert into ers_reimbursement(reimb_amount,reimb_description,reimb_author,reimb_type_id) "
					  + "values('"+ amount + "',  '" + description + "', '" + author + "', '" + type + "')"; 
			  Statement statement = con.createStatement();
			  int changed = statement.executeUpdate(sql);
			  System.out.println("Records inserted" + changed); 
			  } catch(SQLException e) {
				  e.printStackTrace(); } 
		  }



	@Override
	public void setApproved(int reimbID) {
		// TODO Auto-generated method stub
		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			String sql="update ers_reimbursement set reimb_status_id = 2 where reimb_id ='" + reimbID + "';";
			Statement statement = con.createStatement();
			  int changed = statement.executeUpdate(sql);
			  System.out.println("Updated : " + changed); 
		}catch(SQLException e) {
			  e.printStackTrace(); 
			  } 
	  }

	@Override public void setResolved(int reimbID) {
		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			String sql="update ers_reimbursement set reimb_resolved = current_date where reimb_id ='" + reimbID + "';";
			Statement statement = con.createStatement();
			  int changed = statement.executeUpdate(sql);
			  System.out.println("Updated : " + changed); 
		}catch(SQLException e) {
			  e.printStackTrace(); 
			  } 
	}


	@Override
	public void setDenied(int reimbID) {
		// TODO Auto-generated method stub
		try(Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			String sql="update ers_reimbursement set reimb_status_id = 3 where reimb_id ='" + reimbID + "';";
			Statement statement = con.createStatement();
			  int changed = statement.executeUpdate(sql);
			  
		}catch(SQLException e) {
			  e.printStackTrace(); 
		} 
		
	}

	public void insert(double amount, String description, int author, int type) {
		// TODO Auto-generated method stub
		
	}


}
