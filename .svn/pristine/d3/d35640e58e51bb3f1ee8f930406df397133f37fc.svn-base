package com.guigu.salmanager.dao;

import java.util.List;
import com.guigu.salmanager.model.StockOrder;

public interface StockOrderDAO extends BaseDAO<StockOrder>{
	public List<StockOrder> findAll() throws Exception;
	public int saveOrder(StockOrder s) throws Exception;
	public int deleteOrder(String id) throws Exception;
	public StockOrder find(String goods_id) throws Exception;
	public List<StockOrder> findName(String name,int flag) throws Exception;
	public List<StockOrder> find(String warehouse,String category,int flag) throws Exception;
}
