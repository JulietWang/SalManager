package com.guigu.salmanager.dao;

import java.util.List;

import com.guigu.salmanager.model.Goods;

public interface GoodsDAO extends BaseDAO<Goods> {

	public List<Goods> findAllGoods() throws Exception;

	public int saveGoods(Goods goods) throws Exception;

	public int deleteGoods(String id) throws Exception;

	public int updateGoods(Goods goods) throws Exception;

	public List<Goods> findGoods(String category, String warehouse) throws Exception;
	public Goods find(String id) throws Exception;
	
	public Goods findById(String id) throws Exception;

	
}
