package com.guigu.salmanager.dao;

import java.util.List;

import com.guigu.salmanager.model.Customer;

public interface CustomerDAO  extends BaseDAO<Customer>{
	
	public List<Customer> findAllCustomer() throws Exception;
	public List<Customer> findAll() throws Exception;
	public Customer find(String id) throws Exception;
	
	public int saveCustomer(Customer customer) throws Exception;
	
	public int deleteCustomer(String id) throws Exception;
	
	public int updateCustomer(Customer customer) throws Exception;
	
}
