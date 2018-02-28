package com.guigu.salmanager.service.impl;

import java.util.List;

import com.guigu.salmanager.dao.CategoryDAO;
import com.guigu.salmanager.dao.impl.CategoryDAOImpl;
import com.guigu.salmanager.model.Category;
import com.guigu.salmanager.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDAO categoryDAO = new CategoryDAOImpl();

	@Override
	public List<Category> findAll() throws Exception {
		return categoryDAO.findAll();
	}

	@Override
	public Category find(String id) throws Exception {
		return categoryDAO.find(id);
	}

}
