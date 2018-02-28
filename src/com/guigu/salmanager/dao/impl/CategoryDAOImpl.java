package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.CategoryDAO;
import com.guigu.salmanager.model.Category;

public class CategoryDAOImpl  extends BaseDAOImpl<Category> implements CategoryDAO{

	@Override
	public List<Category> findAll() throws Exception {
		String sql="select * from category";
		return this.getForList(conn, sql);
	}

	@Override
	public Category find(String id) throws Exception {
		String sql="select * from category where category_id=?";
		return this.get(conn, sql, id);
	}

}
