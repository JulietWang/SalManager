package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.dao.SupplierDAO;
import com.guigu.salmanager.dao.impl.SupplierDAOImpl;
import com.guigu.salmanager.model.Supplier;
import com.guigu.salmanager.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

	private SupplierDAO supplierDAO = new SupplierDAOImpl();

	@Override
	public Vector<Vector> findAllSupplier() throws Exception {
		List<Supplier> supplier = supplierDAO.findAllSupplier();
		Vector<Vector> rows = new Vector<Vector>();

		if (!supplier.isEmpty()) {
			for (Supplier obj : supplier) {
				//System.out.println(obj.toString());
				Vector temp = new Vector();
				temp.add(obj.getSupplier_Id());
				temp.add(obj.getSupplierSimpleName());
				temp.add(obj.getSupplierName());
				temp.add(obj.getOwner());
				temp.add(obj.getMobiletelephone());
				temp.add(obj.getCompanyAddress());
				temp.add(obj.getFactoryAddress());
				temp.add(obj.getLastPurchaseDate());

				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public int saveSupplier(Supplier supplier) throws Exception {
		return supplierDAO.saveSupplier(supplier);
	}

	@Override
	public int deleteSupplier(String id) throws Exception {
		return supplierDAO.deleteSupplier(id);
	}

	@Override
	public int updateSupplier(Supplier supplier) throws Exception {
		return supplierDAO.updateSupplier(supplier);
	}

	@Override
	public Supplier findId(String id) throws Exception {
		return supplierDAO.findId(id);
	}

	@Override
	public Supplier findName(String name) throws Exception {
		return supplierDAO.findName(name);
	}

}
