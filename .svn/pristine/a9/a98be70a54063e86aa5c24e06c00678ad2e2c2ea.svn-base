package com.guigu.salmanager.service.impl;

import java.util.List;
import java.util.Vector;

import com.guigu.salmanager.dao.WarehouseDAO;
import com.guigu.salmanager.dao.impl.WarehouseDAOImpl;
import com.guigu.salmanager.model.Warehouse;
import com.guigu.salmanager.service.WarehouseService;

public class WarehouseServiceImpl implements WarehouseService {
	private WarehouseDAO warehouseDAO = new WarehouseDAOImpl();

	@Override
	public List<Warehouse> findAllWarehouse() throws Exception {
		return warehouseDAO.findAll();
	}
	

	@Override
	public Warehouse find(String id) throws Exception {
		return warehouseDAO.find(id);
	}

	@Override
	public int saveWarehouse(Warehouse warehouse) throws Exception {
		return warehouseDAO.saveWarehouse(warehouse);
	}

	@Override
	public int deleteWarehouse(String id) throws Exception {
		return warehouseDAO.deleteWarehouse(id);
	}

	@Override
	public int updateWarehouse(Warehouse warehouse) throws Exception {
		return warehouseDAO.updateWarehouse(warehouse);
	}

	@Override
	public Vector<Vector> findAll() throws Exception {
		List<Warehouse> warehouses =warehouseDAO.findAll();
		Vector<Vector> rows = new Vector<Vector>();
		if(!warehouses.isEmpty()) {
			for (Warehouse obj : warehouses) {
				Vector temp = new Vector();
				for(int i=0;i<4;i++) {
					temp.add(obj.getWarehouse_Id());
					temp.add(obj.getName());
					temp.add(obj.getDel_flag());
					temp.add(obj.getSort());
				}
				rows.add(temp);	
			}
			
		}
		return rows;
		}

}
