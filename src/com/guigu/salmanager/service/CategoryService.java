package com.guigu.salmanager.service;

import java.util.List;

import com.guigu.salmanager.model.Category;

public interface CategoryService {
	
	public List<Category> findAll() throws Exception;

	public Category find(String id) throws Exception;
}
