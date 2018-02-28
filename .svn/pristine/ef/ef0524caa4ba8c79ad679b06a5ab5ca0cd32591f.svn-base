package com.guigu.salmanager.dao.impl;

import com.guigu.salmanager.dao.UsersDAO;
import com.guigu.salmanager.model.Users;

public class UsersDAOImpl extends BaseDAOImpl<Users> implements UsersDAO {

	@Override
	public Users login(Users user) throws Exception {
		String sql ="select * from users where name=? and password=?";
		Object [] args= {user.getName(),user.getPassword()};
		return this.get(conn, sql, args);
	}

	@Override
	public int modify(Users user,String newname,String newpassword) throws Exception {
		String sql ="update users set name=? ,password=? , identity='0' where name=?";
		Object [] args= {newname,newpassword,user.getName()};
		return this.update(conn, sql, args);
	}

	@Override
	public Users find(String id) throws Exception {
		String sql="select * from users where user_id=?";
		return this.get(conn, sql, id);
	}

	@Override
	public Users findId(String name) throws Exception {
		String sql ="select * from users where name=?";
		return this.get(conn, sql, name);
	}
	
}
