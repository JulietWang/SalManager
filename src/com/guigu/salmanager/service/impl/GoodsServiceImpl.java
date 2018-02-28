package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.dao.GoodsDAO;
import com.guigu.salmanager.dao.impl.GoodsDAOImpl;
import com.guigu.salmanager.model.Goods;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.WarehouseService;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDAO goodsDAO = new GoodsDAOImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private WarehouseService warehouseService = new WarehouseServiceImpl();

	@Override
	public Vector<Vector> findAllGoods() throws Exception {
		List<Goods> goods = goodsDAO.findAllGoods();
		Vector<Vector> rows = new Vector<Vector>();

		if (!goods.isEmpty()) {
			for (Goods obj : goods) {
				Vector temp = new Vector();
				temp.add(obj.getGoods_Id());
				temp.add(obj.getName());
				temp.add(obj.getPrice());
				temp.add(obj.getOrigin());

				temp.add(categoryService.find(obj.getCategory_Id()).getName());
				temp.add(warehouseService.find(obj.getWarehouse_Id()).getName());
				temp.add(obj.getStock());
				temp.add(obj.getWarehouse_Id());
				temp.add(obj.getCategory_Id());
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public int saveGoods(Goods goods) throws Exception {
		return goodsDAO.saveGoods(goods);
	}

	@Override
	public int deleteGoods(String id) throws Exception {
		return goodsDAO.deleteGoods(id);
	}

	@Override
	public int updateGoods(Goods goods) throws Exception {
		return goodsDAO.updateGoods(goods);
	}

	@Override
	public Vector<Vector> findGoods(String category, String warehouse) throws Exception {
		List<Goods> goods = goodsDAO.findGoods(category, warehouse);
		Vector<Vector> rows = new Vector<Vector>();

		if (!goods.isEmpty()) {
			for (Goods obj : goods) {
				Vector temp = new Vector();
				temp.add(obj.getGoods_Id());
				temp.add(obj.getName());
				temp.add(obj.getPrice());
				temp.add(obj.getOrigin());

				temp.add(categoryService.find(obj.getCategory_Id()).getName());
				temp.add(warehouseService.find(obj.getWarehouse_Id()).getName());
				temp.add(obj.getStock());
				temp.add(obj.getWarehouse_Id());
				temp.add(obj.getCategory_Id());
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Goods find(String id) throws Exception {
		return goodsDAO.find(id);
	}

	@Override
	public List<Goods> findAll() throws Exception {
		return goodsDAO.findAllGoods();
	}

	@Override
	public int updateGoods(Goods goods, double amount, int flag) throws Exception {
		if (flag == 1) {
			goods.setStock(goods.getStock() + amount);
		} else if (flag == 0) {
			goods.setStock(goods.getStock() - amount);
		}
		return goodsDAO.updateGoods(goods);
	}

	@Override
	public Goods findById(String id) throws Exception {
		return goodsDAO.findById(id);
	}

}
