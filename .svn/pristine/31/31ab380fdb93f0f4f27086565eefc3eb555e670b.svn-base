package com.guigu.salmanager.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.guigu.salmanager.dao.BaseDAO;
import com.guigu.salmanager.utils.JdbcUtils;

public class BaseDAOImpl<T> implements BaseDAO<T> {
	private QueryRunner queryRunner = null;
	// 定义类的类型
	private Class<T> type;

	public Connection conn = null;

	public BaseDAOImpl() {
		conn = JdbcUtils.getJdbcUtils().getConnection();
		queryRunner = new QueryRunner();
		// 利用反射得到该类的类型
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		type = (Class) params[0];
	}

	@Override
	public void batch(Connection connection, String sql, Object[]... args) throws SQLException {
		queryRunner.batch(connection, sql, args);
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, Object... args) throws SQLException {
		return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
	}

	@Override
	public T get(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
	}

	@Override
	public int update(Connection connection, String sql, Object... args) throws SQLException {

		return queryRunner.update(connection, sql, args);
	}

	@Override
	public int update(Connection connection, String sql, Object[]... args) throws SQLException {
		return queryRunner.update(connection, sql, args);
	}

}
