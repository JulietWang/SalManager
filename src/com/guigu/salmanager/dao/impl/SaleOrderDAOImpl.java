package com.guigu.salmanager.dao.impl;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections4.functors.AndPredicate;
import org.apache.poi.xssf.eventusermodel.examples.FromHowTo;

import com.guigu.salmanager.dao.SaleOrderDAO;
import com.guigu.salmanager.model.SaleOrder;

public class SaleOrderDAOImpl extends BaseDAOImpl<SaleOrder> implements SaleOrderDAO{

	@Override
	public int saveSalOrder(SaleOrder so) throws Exception {	
		String sql="insert into Sale_Order values(?,?,?,?,?,?,?,?,0)";
		Object[] obj= {so.getSale_id(),so.getBill_no(),so.getSalesman_id(),so.getCategory_id()
				,so.getWarehouse_id(),so.getAmount(),so.getGoods_id(),so.getCustomer_id()};
		return this.update(conn, sql, obj);
	}

	@Override
	public List<SaleOrder> findAllSaleOrder() throws Exception {
		String sql="select s.* from sale_order s  where s.del_flag=0 " ;
		return this.getForList(conn, sql);
	}

	@Override
	public List<SaleOrder> find(String name) throws Exception {
		String sql="select s.* from sale_order s,goods g\r\n" + 
				"where s.goods_id=g.goods_id and g.name like ? and s.del_flag=0 ";
		return this.getForList(conn, sql, "%"+name+"%");
	}

	@Override
	public List<SaleOrder> find(String warehouse, String category) throws Exception {
		if(warehouse.equals("全部")&&category.equals("全部")) {
			String sql="select * from sale_order where del_flag=0 ";
			return this.getForList(conn, sql);
		}else if (warehouse.equals("全部")&&(!category.equals("全部"))) {
			String sql="select s.* from sale_order s,category c\r\n" + 
					"where s.category_id=c.category_id and c.name=? and s.del_flag=0";
			return this.getForList(conn, sql, category); 
		}else if((!warehouse.equals("全部"))&&category.equals("全部")) {
			String sql="select s.* from sale_order s,warehouse w\r\n" + 
					"where s.warehouse_id=w.warehouse_id and w.name=? and s.del_flag=0";
			return this.getForList(conn, sql,warehouse);
		}else {
			String sql="select s.* from sale_order  s ,category c , warehouse w "
					+ "where s.category_id=c.category_id and s.warehouse_id=w.warehouse_id "
					+ "and w.name=? and c.name=? and  s.del_flag=0";
			return this.getForList(conn, sql, warehouse,category);
		}
	}

	@Override
	public int deleteOrder(String id) throws Exception {
		String sql="update sale_order set del_flag=1 where sale_id=?";
		return this.update(conn, sql, id);
	}

}
