package com.guigu.salmanager.service;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.model.Customer;

public interface CustomerService {

	public Vector<Vector> findAllCustomer() throws Exception;

	public int saveCustomer(Customer customer) throws Exception;

	public int deleteCustomer(String id) throws Exception;

	public int updateCustomer(Customer customer) throws Exception;

	public List<Customer> findAll() throws Exception;

	public Customer find(String id) throws Exception;
}
