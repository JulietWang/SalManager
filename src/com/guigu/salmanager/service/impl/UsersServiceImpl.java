package com.guigu.salmanager.service.impl;

import com.guigu.salmanager.dao.UsersDAO;
import com.guigu.salmanager.dao.impl.UsersDAOImpl;
import com.guigu.salmanager.model.Users;
import com.guigu.salmanager.service.UsersService;

public class UsersServiceImpl implements UsersService {
	private UsersDAO dao = new UsersDAOImpl();

	@Override
	public Users login(Users user) throws Exception {
		return dao.login(user);
	}

	@Override
	public int modify(Users user, String newname, String newpassword) throws Exception {
		return dao.modify(user, newname, newpassword);
	}

	@Override
	public Users find(String id) throws Exception {
		return dao.find(id);
	}

	@Override
	public Users findId(String name) throws Exception {
		return dao.findId(name);
	}

}
