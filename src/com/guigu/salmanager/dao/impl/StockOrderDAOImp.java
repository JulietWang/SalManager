package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.StockOrderDAO;
import com.guigu.salmanager.model.SaleOrder;
import com.guigu.salmanager.model.StockOrder;

public class StockOrderDAOImp extends BaseDAOImpl<StockOrder>implements StockOrderDAO{

	@Override
	public List<StockOrder> findAll() throws Exception {
			String sql="select * from Stock_order where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public int saveOrder(StockOrder s) throws Exception {
		String sql="insert into Stock_order values(?,?,?,?,?,?,?,?,?,0)";
		Object[] args= {s.getStock_id(),s.getBill_No(),s.getUser_id(),s.getSupplier_id(),s.getWarehouse_Id(),
				s.getCategory_Id(),s.getAmount(),s.getGoods_Id(),s.getSign()};
		return this.update(conn, sql, args);
	}

	@Override
	public int deleteOrder(String id) throws Exception {
		String sql="update stock_order set del_flag=1 where stock_id=?";
		return this.update(conn, sql, id);
	}
	
	@Override
	public List<StockOrder> findName(String name,int flag) throws Exception {
		String sql="select s.* from stock_order s,goods g\r\n" + 
				"where s.goods_id=g.goods_id and g.name like ?  and s.sign=? and s.del_flag=0";
		return this.getForList(conn, sql, "%"+name+"%",flag);
	}

	@Override
	public StockOrder find(String goods_id) throws Exception {
		String sql="select * from stock_order where goods_id=? and del_flag=0";
		return this.get(conn, sql, goods_id);
	}

	@Override
	public List<StockOrder> find(String warehouse, String category, int flag) throws Exception {
		if(warehouse.equals("全部")&&category.equals("全部")) {
			String sql="select * from stock_order where sign=? and del_flag=0";
			return this.getForList(conn, sql,flag);
		}else if (warehouse.equals("全部")&&(!category.equals("全部"))) {
			String sql="select s.* from stock_order s,category c\r\n" + 
					"where s.category_id=c.category_id and c.name=? and s.sign=?  and s.del_flag=0";
			return this.getForList(conn, sql, category,flag); 
		}else if((!warehouse.equals("全部"))&&category.equals("全部")) {
			String sql="select s.* from stock_order s,warehouse w\r\n" + 
					"where s.warehouse_id=w.warehouse_id and w.name=? and s.sign=?  and s.del_flag=0";
			return this.getForList(conn, sql,warehouse,flag);
		}else {
			String sql="select s.* from stock_order s ,category c , warehouse w "
					+ "where s.category_id=c.category_id and s.warehouse_id=w.warehouse_id "
					+ "and w.name=? and c.name=? and s.sign=?   and s.del_flag=0";
			return this.getForList(conn, sql, warehouse,category,flag);
		}
	}

}
