package com.guigu.salmanager.dao;

import java.util.List;

import com.guigu.salmanager.model.Supplier;

public interface SupplierDAO extends BaseDAO<Supplier> {

	public List<Supplier> findAllSupplier() throws Exception;

	public int saveSupplier(Supplier supplier) throws Exception;

	public int deleteSupplier(String id) throws Exception;

	public int updateSupplier(Supplier supplier) throws Exception;
	public Supplier findId(String id) throws Exception;
	public Supplier findName(String name) throws Exception;
}
