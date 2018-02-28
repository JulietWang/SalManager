package com.guigu.salmanager.model;

public class Customer {
	private String customer_Id;
	private String customerSimpleName;
	private String customerName;
	private String owner;
	private String mobilephone;
	private String salesman_Id;
	private String cutomerAddress;
	private String lastDeliverDate;
	private int del_flag;

	public Customer() {
		super();
	}

	public Customer(String customer_Id, String customerSimpleName, String customerName, String owner,
			String mobilephone, String salesman_Id, String cutomerAddress, String lastDeliverDate, int del_flag) {
		super();
		this.customer_Id = customer_Id;
		this.customerSimpleName = customerSimpleName;
		this.customerName = customerName;
		this.owner = owner;
		this.mobilephone = mobilephone;
		this.salesman_Id = salesman_Id;
		this.cutomerAddress = cutomerAddress;
		this.lastDeliverDate = lastDeliverDate;
		this.del_flag = del_flag;
	}

	public String getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getCustomerSimpleName() {
		return customerSimpleName;
	}

	public void setCustomerSimpleName(String customerSimpleName) {
		this.customerSimpleName = customerSimpleName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getSalesman_Id() {
		return salesman_Id;
	}

	public void setSalesman_Id(String salesman_Id) {
		this.salesman_Id = salesman_Id;
	}

	public String getCutomerAddress() {
		return cutomerAddress;
	}

	public void setCutomerAddress(String cutomerAddress) {
		this.cutomerAddress = cutomerAddress;
	}

	public String getLastDeliverDate() {
		return lastDeliverDate;
	}

	public void setLastDeliverDate(String lastDeliverDate) {
		this.lastDeliverDate = lastDeliverDate;
	}

	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}

	@Override
	public String toString() {
		return "Customer [customer_Id=" + customer_Id + ", customerSimpleName=" + customerSimpleName + ", customerName="
				+ customerName + ", owner=" + owner + ", mobilephone=" + mobilephone + ", salesman_Id=" + salesman_Id
				+ ", cutomerAddress=" + cutomerAddress + ", lastDeliverDate=" + lastDeliverDate + ", del_flag="
				+ del_flag + "]";
	}

}
