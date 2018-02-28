package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.dao.SalesmanDAO;
import com.guigu.salmanager.dao.impl.SalesmanDAOImpl;
import com.guigu.salmanager.model.Salesman;
import com.guigu.salmanager.model.Salesman;
import com.guigu.salmanager.service.SalesmanService;

public class SalesmanServiceImpl implements SalesmanService {

	private SalesmanDAO salesmanDAO = new SalesmanDAOImpl();

	@Override
	public Salesman find(String id) throws Exception {
		return salesmanDAO.find(id);
	}

	@Override
	public int saveSalesman(Salesman salesman) throws Exception {
		return salesmanDAO.saveSalesman(salesman);
	}

	@Override
	public int deleteSalesman(String id) throws Exception {
		return salesmanDAO.deleteSalesman(id);
	}

	@Override
	public int updateSalesman(Salesman salesman) throws Exception {
		return salesmanDAO.updateSalesman(salesman);
	}

	@Override
	public Vector<Vector> findAllSalesman() throws Exception {
		List<Salesman> salesman = salesmanDAO.findAllSalesman();

		Vector<Vector> rows = new Vector<Vector>();

		if (!salesman.isEmpty()) {
			for (Salesman obj : salesman) {
				Vector temp = new Vector();
				temp.add(obj.getSalesman_Id());
				temp.add(obj.getSalesmanName());
				temp.add(obj.getMobiletelephone());
				temp.add(obj.getContactAddress());
				temp.add(obj.getEmail());
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public List<Salesman> findAll() throws Exception {
		return salesmanDAO.findAll();
	}

	@Override
	public Salesman findId(String id) throws Exception {
		return salesmanDAO.findId(id) ;
	}

	@Override
	public Salesman findName(String name) throws Exception {
		return salesmanDAO.findId(name) ;
	}

}
