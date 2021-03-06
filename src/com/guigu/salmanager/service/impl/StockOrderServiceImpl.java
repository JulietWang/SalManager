package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.dao.StockOrderDAO;
import com.guigu.salmanager.dao.impl.StockOrderDAOImp;
import com.guigu.salmanager.model.StockOrder;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.StockOrderService;
import com.guigu.salmanager.service.SupplierService;
import com.guigu.salmanager.service.UsersService;
import com.guigu.salmanager.service.WarehouseService;

public class StockOrderServiceImpl implements StockOrderService {
	StockOrderDAO sOrderDAO = new StockOrderDAOImp();
	UsersService userService = new UsersServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();
	SupplierService supplierService = new SupplierServiceImpl();
	GoodsService goods = new GoodsServiceImpl();

	@Override
	public List<StockOrder> findAll() throws Exception {
		return sOrderDAO.findAll();
	}

	@Override
	public int saveOrder(StockOrder s) throws Exception {
		return sOrderDAO.saveOrder(s);
	}

	@Override
	public int deleteOrder(String id) throws Exception {
		return sOrderDAO.deleteOrder(id);
	}

	@Override
	public Vector<Vector> findAllOrder(int flag) throws Exception {
		List<StockOrder> stockOrder = sOrderDAO.findAll();
		Vector<Vector> rows = new Vector<Vector>();

		if (!stockOrder.isEmpty()) {
			for (StockOrder obj : stockOrder) {
				if (obj.getSign() == flag) {
					Vector temp = new Vector();
					temp.add(obj.getStock_id());
					temp.add(obj.getBill_No());
					temp.add(userService.find(obj.getUser_id()).getName());
					temp.add(supplierService.findId(obj.getSupplier_id()).getSupplierName());
					
					//System.out.println(obj.getSupplier_id());
					temp.add(warehouseService.find(obj.getWarehouse_Id()).getName());
					temp.add(categoryService.find(obj.getCategory_Id()).getName());
					temp.add(obj.getAmount());
					temp.add(goods.find(obj.getGoods_Id()).getName());
					rows.add(temp);
				}
			}
		}
		return rows;
	}

	@Override
	public StockOrder find(String goods_id) throws Exception {
		return sOrderDAO.find(goods_id);
	}

	@Override
	public Vector<Vector> find(String warehouse, String category, int flag) throws Exception {
		List<StockOrder> sList = sOrderDAO.find(warehouse, category, flag);
		Vector<Vector> row = new Vector<>();
		if (!sList.isEmpty()) {
			for (StockOrder s : sList) {
				Vector temp = new Vector();
				for (int i = 0; i < 8; i++) {
					temp.add(s.getStock_id());
					temp.add(s.getBill_No());
					temp.add(userService.find(s.getUser_id()).getName());
					temp.add(supplierService.findId(s.getSupplier_id()).getSupplierSimpleName());
					temp.add(categoryService.find(s.getCategory_Id()).getName());
					temp.add(warehouseService.find(s.getWarehouse_Id()).getName());
					temp.add(s.getAmount());
					temp.add(goods.find(s.getGoods_Id()).getName());
				}
				row.add(temp);
			}
		}
		return row;
	}

	@Override
	public Vector<Vector> findName(String name, int flag) throws Exception {
		List<StockOrder> sList = sOrderDAO.findName(name, flag);
		Vector<Vector> row = new Vector<>();
		if (!sList.isEmpty()) {
			for (StockOrder s : sList) {
				if (s.getSign() == flag) {
					Vector temp = new Vector();
					for (int i = 0; i < 8; i++) {
						temp.add(s.getStock_id());
						temp.add(s.getBill_No());
						temp.add(userService.find(s.getUser_id()).getName());
						temp.add(supplierService.findId(s.getSupplier_id()).getSupplierSimpleName());
						temp.add(categoryService.find(s.getCategory_Id()).getName());
						temp.add(warehouseService.find(s.getWarehouse_Id()).getName());
						temp.add(s.getAmount());
						temp.add(goods.find(s.getGoods_Id()).getName());
					}
					row.add(temp);
				}
			}
		}
		return row;
	}

}
