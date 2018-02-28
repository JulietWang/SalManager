package com.guigu.salmanager.service;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.model.Warehouse;

public interface WarehouseService {

	public List<Warehouse> findAllWarehouse() throws Exception;
	
	public Vector<Vector> findAll() throws Exception;

	public Warehouse find(String id) throws Exception;
	
	public int saveWarehouse(Warehouse warehouse) throws Exception;

	public int deleteWarehouse(String id) throws Exception;

	public int updateWarehouse(Warehouse warehouse) throws Exception;}
