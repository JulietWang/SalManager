package com.guigu.salmanager.dao;

import java.util.List;

import com.guigu.salmanager.model.Category;

public interface CategoryDAO  extends BaseDAO<Category>{

	public List<Category> findAll() throws Exception;
	
	public  Category find(String id) throws Exception;
}
