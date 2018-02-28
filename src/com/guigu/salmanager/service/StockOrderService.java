package com.guigu.salmanager.service;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.model.Goods;
import com.guigu.salmanager.model.StockOrder;

public interface StockOrderService {
	public int saveOrder(StockOrder s) throws Exception;
	public int deleteOrder(String id) throws Exception;
	public Vector<Vector> findAllOrder(int flag) throws Exception;
	public List<StockOrder> findAll() throws Exception;
	public Vector<Vector> findName(String name,int flag) throws Exception;
	public StockOrder find(String goods_id) throws Exception;
	public Vector<Vector> find(String warehouse,String category,int flag) throws Exception;
}
