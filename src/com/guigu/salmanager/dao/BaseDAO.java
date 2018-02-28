package com.guigu.salmanager.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
	/**
	 * 批量处理的方法
	 * @param connection
	 * @param sql
	 * @param args 填充占位符
	 * @throws SQLException
	 */
	 void batch(Connection connection,String sql,Object[] ...args) throws SQLException;
	 
	 /**
	  * 返回一个具体的值   比如 查询总的人数  查询某个人的email
	  * @param connection
	  * @param sql
	  * @param args
	  * @return
	  * @throws SQLException
	  */
	 <E> E getForValue(Connection connection,String sql,Object ...args) throws SQLException;
	 /**
	  * 返回一个T类型的列表
	  * @param connection
	  * @param sql
	  * @param args
	  * @return
	  * @throws SQLException
	  */
	 List<T> getForList(Connection connection,String sql,Object ...args) throws SQLException;
	 
	 /**
	  * 返回一个T类型的对象
	  * @param connection
	  * @param sql
	  * @param args
	  * @return
	  * @throws SQLException
	  */
	 T get(Connection connection,String sql,Object...args)throws SQLException;
	 
	 /**
	  * INSERT UPDATE DELETE都可以使用这个方法
	  * @param connection
	  * @param sql
	  * @param args
	  * @throws SQLException
	  */
	 int update(Connection connection,String sql, Object ...args) throws SQLException;
	 
	 int update(Connection connection,String sql, Object[] ...args) throws SQLException;
	 
}
