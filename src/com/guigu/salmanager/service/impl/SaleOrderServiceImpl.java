package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

import com.guigu.salmanager.dao.SaleOrderDAO;
import com.guigu.salmanager.dao.impl.SaleOrderDAOImpl;
import com.guigu.salmanager.model.Customer;
import com.guigu.salmanager.model.SaleOrder;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.CustomerService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.SaleOrderService;
import com.guigu.salmanager.service.SalesmanService;
import com.guigu.salmanager.service.UsersService;
import com.guigu.salmanager.service.WarehouseService;

import oracle.net.aso.s;

public class SaleOrderServiceImpl implements SaleOrderService{
	private SaleOrderDAO saleOrderDao=new SaleOrderDAOImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private WarehouseService warehouseService = new WarehouseServiceImpl();
	private GoodsService goodsService=new GoodsServiceImpl();
	private UsersService userService=new UsersServiceImpl();
	private SalesmanService salesman=new SalesmanServiceImpl();
	private CustomerService customerservice=new CustomerServiceImpl();

	@Override
	public int saveSaleOrder(SaleOrder so) throws Exception {
		return saleOrderDao.saveSalOrder(so);
	}

	@Override
	public Vector<Vector> findAllSaleOrder() throws Exception {
		List<SaleOrder> sList=saleOrderDao.findAllSaleOrder();
		Vector<Vector> rows=new	Vector<>();
		if(!sList.isEmpty()) {
			for(SaleOrder s:sList) {
				Vector temp=new Vector();
				for(int i=0;i<8;i++) {
					temp.add(s.getSale_id());
					temp.add(s.getBill_no());
					temp.add(goodsService.find(s.getGoods_id()).getName());
					temp.add(s.getAmount());
					temp.add(categoryService.find(s.getCategory_id()).getName());
					temp.add(warehouseService.find(s.getWarehouse_id()).getName());
					temp.add(salesman.findId(s.getSalesman_id()).getSalesmanName());
					temp.add(customerservice.find(s.getCustomer_id()).getCustomerSimpleName());
				}
				rows.add(temp);
				
			}	
		}
		return rows;
	}

	@Override
	public Vector<Vector> find(String name) throws Exception {
		List<SaleOrder> sList=saleOrderDao.find(name);
		Vector<Vector> row=new Vector<>();
		if(!sList.isEmpty()) {
			for (SaleOrder s : sList) {
				Vector temp=new Vector();
				for(int i=0;i<8;i++) {
					temp.add(s.getSale_id());
					temp.add(s.getBill_no());
					temp.add(goodsService.find(s.getGoods_id()).getName());
					temp.add(s.getAmount());
					temp.add(categoryService.find(s.getCategory_id()).getName());
					temp.add(warehouseService.find(s.getWarehouse_id()).getName());
					temp.add(salesman.findId(s.getSalesman_id()).getSalesmanName());
					temp.add(customerservice.find(s.getCustomer_id()).getCustomerSimpleName());
				}
			row.add(temp);
			}
		}
		return row;
	}

	@Override
	public Vector<Vector> find(String warehouse, String category) throws Exception {
		List<SaleOrder> sList=saleOrderDao.find(warehouse,category);
		Vector<Vector> row=new Vector<>();
		if(!sList.isEmpty()) {
			for (SaleOrder s : sList) {
				Vector temp=new Vector();
				for(int i=0;i<8;i++) {
					temp.add(s.getSale_id());
					temp.add(s.getBill_no());
					temp.add(goodsService.find(s.getGoods_id()).getName());
					temp.add(s.getAmount());
					temp.add(categoryService.find(s.getCategory_id()).getName());
					temp.add(warehouseService.find(s.getWarehouse_id()).getName());
					temp.add(salesman.findId(s.getSalesman_id()).getSalesmanName());
					temp.add(customerservice.find(s.getCustomer_id()).getCustomerSimpleName());
				}
			row.add(temp);
			}
		}
		return row;
	}

	@Override
	public int deleteOrder(String id) throws Exception {
		return saleOrderDao.deleteOrder(id);
	}


}
