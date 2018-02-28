package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.CustomerDAO;
import com.guigu.salmanager.model.Customer;

public class CustomerDAOImpl extends BaseDAOImpl<Customer> implements CustomerDAO {

	@Override
	public List<Customer> findAllCustomer() throws Exception {
		String sql = "select * from Customer where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public int saveCustomer(Customer customer) throws Exception {
		String sql = "insert into Customer values(?,?,?,?,?,?,?,?,0)";
		Object[] args = { customer.getCustomer_Id(), customer.getCustomerSimpleName(), customer.getCustomerName(),
				customer.getOwner(), customer.getMobilephone(), customer.getSalesman_Id(), customer.getCutomerAddress(),
				customer.getLastDeliverDate() };
		return this.update(conn, sql, args);
	}

	@Override
	public int deleteCustomer(String id) throws Exception {
		String sql = "update customer set del_flag=1 where customer_id=?";
		return this.update(conn, sql, id);
	}

	@Override
	public int updateCustomer(Customer customer) throws Exception {
		String sql = "update Customer set customersimplename=?,customername=?,owner=?,mobilephone=?,salesman_id=?,cutomeraddress=?,lastdeliverdate=? where customer_id=?";
		Object[] args = { customer.getCustomerSimpleName(), customer.getCustomerName(), customer.getOwner(),
				customer.getMobilephone(), customer.getSalesman_Id(), customer.getCutomerAddress(),
				customer.getLastDeliverDate(), customer.getCustomer_Id() };
		return this.update(conn, sql, args);
	}

	@Override
	public List<Customer> findAll() throws Exception {
		String sql="select * from customer where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public Customer find(String id) throws Exception {
		String sql="select * from customer where customer_id=?";
		return this.get(conn, sql, id);
	}

}
