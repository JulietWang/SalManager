package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.dao.CustomerDAO;
import com.guigu.salmanager.dao.impl.CustomerDAOImpl;
import com.guigu.salmanager.model.Customer;
import com.guigu.salmanager.service.CustomerService;
import com.guigu.salmanager.service.SalesmanService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO = new CustomerDAOImpl();
	private SalesmanService salesmanService = new SalesmanServiceImpl();

	@Override
	public Vector<Vector> findAllCustomer() throws Exception {
		List<Customer> customer = customerDAO.findAllCustomer();

		Vector<Vector> rows = new Vector<Vector>();

		if (!customer.isEmpty()) {
			for (Customer obj : customer) {
				Vector temp = new Vector();
				//System.out.println(obj.toString());
				temp.add(obj.getCustomer_Id());
				temp.add(obj.getCustomerSimpleName());
				temp.add(obj.getCustomerName());
				temp.add(obj.getOwner());
				temp.add(obj.getMobilephone());
				temp.add(salesmanService.find(obj.getSalesman_Id()).getSalesmanName());
				temp.add(obj.getCutomerAddress());
				temp.add(obj.getLastDeliverDate());

				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public int saveCustomer(Customer customer) throws Exception {
		return customerDAO.saveCustomer(customer);
	}

	@Override
	public int deleteCustomer(String id) throws Exception {
		return customerDAO.deleteCustomer(id);
	}

	@Override
	public int updateCustomer(Customer customer) throws Exception {
		return customerDAO.updateCustomer(customer);
	}

	@Override
	public List<Customer> findAll() throws Exception {
		// TODO Auto-generated method stub
		 return customerDAO.findAll();
	}

	@Override
	public Customer find(String id) throws Exception {
		return customerDAO.find(id);
	}

}
