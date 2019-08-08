package com.cg.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.exception.InsufficientFundException;
import com.cg.service.AccountServiceImpl;

class AccountServiceTest {
	AccountServiceImpl service;
	AccountDAO mockdao;
	
	
	@BeforeEach
	public void setup() {
		service = new AccountServiceImpl();
		mockdao = EasyMock.createMock(AccountDAO.class);
		service.setAccountDAOImpl(mockdao);
	}
	
	
	@Test
	public void testAddAccount1() {
		Account account = new Account(101, 1234567895L, "James", 25000);
		EasyMock.expect(mockdao.addAccount(account)).andReturn(true);
		EasyMock.replay(mockdao);
		try {
			assertEquals(true, service.addAccount(account));
		} catch (InsufficientFundException e) {
			System.out.println(e.getMessage());
		}
		EasyMock.verify(mockdao);
	}
	
	
	@Test
	public void testUpdateAccount1() {
		Account account = new Account(101, 1234567895L, "Krish", 25000);
		EasyMock.expect(mockdao.updateAccount(account)).andReturn(true);
		EasyMock.replay(mockdao);
		assertEquals(true, service.updateAccount(account));
		EasyMock.verify(mockdao);
	}
	
	
	@Test
	public void testUpdateAccount2(){
		Account account = new Account(101, 1234567895L, "Krish", 25000);
		EasyMock.expect(mockdao.updateAccount(account)).andReturn(true);
		EasyMock.replay(mockdao);
		assertEquals(true, service.updateAccount(account));
		EasyMock.verify(mockdao);
	}
	
	@Test
	public void testFind(){
		long mobile = 1234567895;
		Account account = new Account(101, 1234567895, "Krish", 25000);
		EasyMock.expect(mockdao.findAccount(mobile)).andReturn(account);
		EasyMock.replay(mockdao);
		assertEquals(account, service.findAccount(mobile));
		EasyMock.verify(mockdao);
	}
	
	
}
