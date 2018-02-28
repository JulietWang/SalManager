package com.guigu.salmanager.service;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.model.Salesman;
import com.guigu.salmanager.model.Warehouse;

public interface SalesmanService {

	public Salesman find(String id) throws Exception;
	
	public int saveSalesman(Salesman salesman) throws Exception;

	public int deleteSalesman(String id) throws Exception;

	public int updateSalesman(Salesman salesman) throws Exception;
	
	public Vector<Vector> findAllSalesman() throws Exception;
	
	public List<Salesman> findAll() throws Exception;
	public Salesman findId(String id) throws Exception;
	public Salesman findName(String name) throws Exception;
}
