package com.guigu.salmanager.dao;

import java.util.List;

import com.guigu.salmanager.model.SaleOrder;

public interface SaleOrderDAO extends BaseDAO<SaleOrder>{
	public List<SaleOrder> findAllSaleOrder() throws Exception;
	public int saveSalOrder(SaleOrder so) throws Exception;
	public List<SaleOrder> find(String name) throws Exception;
	public List<SaleOrder> find(String warehouse,String category) throws Exception;
	public int deleteOrder(String id) throws Exception;
}
