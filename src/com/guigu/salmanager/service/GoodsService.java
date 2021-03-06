package com.guigu.salmanager.service;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.model.Goods;

public interface GoodsService {

	public Vector<Vector> findAllGoods() throws Exception;

	public int saveGoods(Goods goods) throws Exception;

	public int deleteGoods(String id) throws Exception;

	public int updateGoods(Goods goods) throws Exception;

	public Goods find(String id) throws Exception;
	
	public Goods findById(String id) throws Exception;
	
	public List<Goods> findAll() throws Exception;
	
	public int updateGoods(Goods goods,double amount,int flag) throws Exception;

	public Vector<Vector> findGoods(String category, String warehouse) throws Exception;
}
