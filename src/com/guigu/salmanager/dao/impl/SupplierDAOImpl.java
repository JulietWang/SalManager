package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.SupplierDAO;
import com.guigu.salmanager.model.Supplier;

public class SupplierDAOImpl extends BaseDAOImpl<Supplier> implements SupplierDAO {

	@Override
	public List<Supplier> findAllSupplier() throws Exception {

		String sql = "select * from supplier where del_flag=0";


		return this.getForList(conn, sql);
	}

	@Override
	public int saveSupplier(Supplier supplier) throws Exception {
		String sql = "insert into supplier values(?,?,?,?,?,?,?,?,0)";
		Object[] args = { supplier.getSupplier_Id(), supplier.getSupplierSimpleName(), supplier.getSupplierName(),
				supplier.getOwner(), supplier.getMobiletelephone(), supplier.getCompanyAddress(),
				supplier.getFactoryAddress(), supplier.getLastPurchaseDate() };
		return this.update(conn, sql, args);
	}

	@Override
	public int deleteSupplier(String id) throws Exception {
		String sql = "update supplier set del_flag=1 where supplier_Id=?";
		return this.update(conn, sql, id);
	}

	@Override
	public int updateSupplier(Supplier supplier) throws Exception {
		String sql = "update supplier set suppliersimplename=?,suppliername=?,owner=?,mobiletelephone =?,companyaddress=?,factoryaddress=?,lastpurchasedate=? where supplier_id=?";
		Object[] args = { supplier.getSupplierSimpleName(), supplier.getSupplierName(), supplier.getOwner(),
				supplier.getMobiletelephone(), supplier.getCompanyAddress(), supplier.getFactoryAddress(),
				supplier.getLastPurchaseDate(), supplier.getSupplier_Id() };
		return this.update(conn, sql, args);
	}

	@Override
	public Supplier findId(String id) throws Exception {
		String sql="select * from Supplier where supplier_Id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public Supplier findName(String name) throws Exception {
		String sql="select * from Supplier where suppliersimplename=?";
		return this.get(conn, sql, name);
	}
}
