package com.guigu.salmanager.model;

public class Warehouse {
	private String warehouse_Id;

	private String name;

	private int del_flag;
	
	private int sort;

	public Warehouse() {
		super();
	}

	

	public Warehouse(String warehouse_Id, String name, int del_flag, int sort) {
		super();
		this.warehouse_Id = warehouse_Id;
		this.name = name;
		this.del_flag = del_flag;
		this.sort = sort;
	}



	public String getWarehouse_Id() {
		return warehouse_Id;
	}

	public void setWarehouse_Id(String warehouse_Id) {
		this.warehouse_Id = warehouse_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	

}
