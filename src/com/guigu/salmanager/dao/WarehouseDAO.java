package com.guigu.salmanager.dao;

import java.util.List;

import com.guigu.salmanager.model.Warehouse;

public interface WarehouseDAO extends BaseDAO<Warehouse> {

	public List<Warehouse> findAll() throws Exception;

	public Warehouse find(String id) throws Exception;
	
	public int saveWarehouse(Warehouse warehouse) throws Exception;

	public int deleteWarehouse(String id) throws Exception;

	public int updateWarehouse(Warehouse warehouse) throws Exception;
	
	public int ifhasgoods(String id ) throws Exception;
}
