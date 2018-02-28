package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.WarehouseDAO;
import com.guigu.salmanager.model.Warehouse;

public class WarehouseDAOImpl extends BaseDAOImpl<Warehouse> implements WarehouseDAO {

	@Override
	public List<Warehouse> findAll() throws Exception {
		String sql = "select * from warehouse where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public Warehouse find(String id) throws Exception {
		String sql = "select * from warehouse where del_flag=0 and warehouse_id=?";
		return this.get(conn, sql, id);
	}
	
	@Override
	public int saveWarehouse(Warehouse warehouse) throws Exception {
		String sql="insert into warehouse (warehouse_id,name,del_flag,sort) values(?,?,0,?)";
		Object[] args= {warehouse.getWarehouse_Id(),warehouse.getName(),warehouse.getSort()};
		return this.update(conn, sql, args);
	}

	@Override
	public int deleteWarehouse(String id) throws Exception {
		String sql="update warehouse set del_flag=1 where warehouse_id=?";
		return this.update(conn, sql, id);
	}

	@Override
	public int updateWarehouse(Warehouse warehouse) throws Exception {
		String sql="update warehouse set name=?,sort=? where warehouse_id=?";
		Object[] args= {warehouse.getName(),warehouse.getSort(),warehouse.getWarehouse_Id()};
		return this.update(conn, sql, args);
	}

	@Override
	public int ifhasgoods(String id) throws Exception {
		String sql="select * from goods where warehouse_id=?";
		return this.update(conn, sql, id);
	}
}
