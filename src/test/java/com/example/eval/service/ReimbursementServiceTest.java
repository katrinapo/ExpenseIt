package com.example.eval.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import com.example.dao.ReimbDAOImpl;
import com.example.model.Reimbursement;

import com.example.service.ReimbursementService;


public class ReimbursementServiceTest {

	
	@Mock
	private ReimbDAOImpl fakeDao;
	private ReimbursementService rServ;
	
	private Reimbursement approvedReimb;
	private Reimbursement approvedReimb2;



@BeforeEach
public void setUp() {
	MockitoAnnotations.initMocks(this);
	
	rServ = new ReimbursementService(fakeDao);
	approvedReimb = new Reimbursement();
	approvedReimb2 = new Reimbursement();
	approvedReimb.setReimbStatusId(2);
	approvedReimb2.setReimbStatusId(2);
	approvedReimb2.setReimbAuthor(4);
	approvedReimb.setReimbAuthor(4);
	//when(((OngoingStubbing) fakeDao.getAll()).thenReturn(new ArrayList<Reimbursement>(Arrays.asList(approvedReimb))));
	
	when(fakeDao.getAll()).thenReturn(new ArrayList<Reimbursement>(Arrays.asList(approvedReimb,approvedReimb2)));

	
	}

	@Test
	public void getAllSuccess() {
		assertEquals(new ArrayList<Reimbursement>(Arrays.asList(approvedReimb,approvedReimb2)),rServ.getAllReimbursements(),"success");
	}
	//BigDecimal amount, String description, int author, int type
	@Test
	public void insertReimbSuccess() {
		assertEquals("success", rServ.insertReimbursement(new BigDecimal("0.04"), "lalala", 4, 5));
	}
	
	
	
}