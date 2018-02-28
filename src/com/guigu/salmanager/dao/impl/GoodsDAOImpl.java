package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.GoodsDAO;
import com.guigu.salmanager.model.Goods;
import com.guigu.salmanager.utils.JdbcUtils;

public class GoodsDAOImpl extends BaseDAOImpl<Goods> implements GoodsDAO {

	@Override
	public List<Goods> findAllGoods() throws Exception {
		String sql = "select * from goods where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public int saveGoods(Goods goods) throws Exception {
		String sql = "insert into goods values(?,?,?,?,?,?,?,0)";
		Object[] args = { goods.getGoods_Id(), goods.getName(), goods.getPrice(), goods.getOrigin(), goods.getStock(),
				goods.getWarehouse_Id(), goods.getCategory_Id() };
		return this.update(conn, sql, args);
	}

	@Override
	public int deleteGoods(String id) throws Exception {
		String sql = "update goods set del_flag=1,stock=0 where goods_id=?";

		return this.update(conn, sql, id);
	}

	@Override
	public int updateGoods(Goods goods) throws Exception {
		String sql = "update goods set name=?,price=?,origin=?,stock=?,warehouse_id=?,category_id=? where goods_id=?";
		Object[] args = { goods.getName(), goods.getPrice(), goods.getOrigin(), goods.getStock(),
				goods.getWarehouse_Id(), goods.getCategory_Id(), goods.getGoods_Id() };
		return this.update(conn, sql, args);
	}

	@Override
	public List<Goods> findGoods(String category, String warehouse) throws Exception {
		if ("全部".equals(category) && "全部".equals(warehouse)) {
			String sql = "select * from goods where del_flag=0";
			return this.getForList(conn, sql);
		} else if ("全部".equals(category) && !"全部".equals(warehouse)) {
			String sql = "select g.* from goods g,warehouse w where g.warehouse_id=w.warehouse_id and g.del_flag=0 and w.name=?";
			return this.getForList(conn, sql, warehouse);
		} else if (!"全部".equals(category) && "全部".equals(warehouse)) {
			String sql = "select g.* from goods g,category c where g.category_id=c.category_id and g.del_flag=0 and c.name=?";
			return this.getForList(conn, sql, category);
		} else {
			String sql = "select g.* from goods g,category c,warehouse w where g.category_id=c.category_id and g.del_flag=0 and  g.warehouse_id=w.warehouse_id and c.name=? and w.name=?";
			return this.getForList(conn, sql, category, warehouse);
		}

	}

	@Override
	public Goods find(String id) throws Exception {
		String sql = "select * from goods where  goods_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public Goods findById(String id) throws Exception {
		String sql = "select * from goods where del_flag=0 and goods_id=?";
		return this.get(conn, sql, id);
	}

}
