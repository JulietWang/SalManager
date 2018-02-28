package com.guigu.salmanager.dao.impl;

import java.util.List;

import com.guigu.salmanager.dao.SalesmanDAO;
import com.guigu.salmanager.model.Salesman;

public class SalesmanDAOImpl extends BaseDAOImpl<Salesman> implements SalesmanDAO {

	@Override
	public Salesman find(String id) throws Exception {
		String sql = "select * from salesman s where salesman_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public int saveSalesman(Salesman salesman) throws Exception {
		String sql = "insert into salesman values(?,?,?,?,?,0)";
		Object[] args = { salesman.getSalesman_Id(), salesman.getSalesmanName(), salesman.getMobiletelephone(),
				salesman.getContactAddress(), salesman.getEmail() };
		return this.update(conn, sql, args);
	}

	@Override
	public int deleteSalesman(String id) throws Exception {
		String sql = "update salesman set del_flag=1 where salesman_id=?";
		return this.update(conn, sql, id);
	}

	@Override
	public int updateSalesman(Salesman salesman) throws Exception {
		String sql = "update salesman set salesmanname=?,mobiletelephone=?,contactaddress=?,email=? where salesman_id=?";
		Object[] args = { salesman.getSalesmanName(), salesman.getMobiletelephone(), salesman.getContactAddress(),
				salesman.getEmail(), salesman.getSalesman_Id() };
		return this.update(conn, sql, args);
	}

	@Override
	public List<Salesman> findAllSalesman() throws Exception {
		String sql = "select * from  salesman where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public List<Salesman> findAll() throws Exception {
		String sql = "select * from  salesman where del_flag=0";
		return this.getForList(conn, sql);
	}

	@Override
	public Salesman findId(String id) throws Exception {
		String sql="select * from SalesMan where SalesMan_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public Salesman findName(String name) throws Exception {
		String sql="select * from SalesMan where salesManname=?";
		return this.get(conn, sql, name);
	}

}
