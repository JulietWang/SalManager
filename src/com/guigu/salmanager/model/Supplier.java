package com.guigu.salmanager.model;

public class Supplier {

	private String supplier_Id;
	private String supplierSimpleName;
	private String supplierName;
	private String owner;
	private String mobiletelephone;
	private String companyAddress;
	private String factoryAddress;
	private String lastPurchaseDate;
	private int del_flag; // '删除标识{1表示已删除，0表示未删除}'

	public Supplier() {
		super();
	}

	public Supplier(String supplier_Id, String supplierSimpleName, String supplierName, String owner,
			String mobiletelephone, String companyAddress, String factoryAddress, String lastPurchaseDate,
			int del_flag) {
		super();
		this.supplier_Id = supplier_Id;
		this.supplierSimpleName = supplierSimpleName;
		this.supplierName = supplierName;
		this.owner = owner;
		this.mobiletelephone = mobiletelephone;
		this.companyAddress = companyAddress;
		this.factoryAddress = factoryAddress;
		this.lastPurchaseDate = lastPurchaseDate;
		this.del_flag = del_flag;
	}

	public String getSupplier_Id() {
		return supplier_Id;
	}

	public void setSupplier_Id(String supplier_Id) {
		this.supplier_Id = supplier_Id;
	}

	public String getSupplierSimpleName() {
		return supplierSimpleName;
	}

	public void setSupplierSimpleName(String supplierSimpleName) {
		this.supplierSimpleName = supplierSimpleName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMobiletelephone() {
		return mobiletelephone;
	}

	public void setMobiletelephone(String mobiletelephone) {
		this.mobiletelephone = mobiletelephone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(String lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}

	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}

	@Override
	public String toString() {
		return "Supplier [supplier_Id=" + supplier_Id + ", supplierSimpleName=" + supplierSimpleName + ", supplierName="
				+ supplierName + ", owner=" + owner + ", mobiletelephone=" + mobiletelephone + ", companyAddress="
				+ companyAddress + ", factoryAddress=" + factoryAddress + ", lastPurchaseDate=" + lastPurchaseDate
				+ ", del_flag=" + del_flag + "]";
	}

}
