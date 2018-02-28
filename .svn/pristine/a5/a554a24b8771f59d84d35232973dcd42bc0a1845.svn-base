package com.guigu.salmanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {

	// 定义用户名
	private static final String username;
	// 定义密码
	private static final String password;
	// 定义路径
	private static final String url;
	// 定义驱动
	private static final String driver;

	private static JdbcUtils jdbcUtils;

	// 使用单例模式
	private JdbcUtils() {

	}

	static {
		Properties properties = new Properties();
		// 通过类加载器 把指定的文件通过流的形式加载进来
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		username = properties.getProperty("jdbc.username");
		password = properties.getProperty("jdbc.password");
		url = properties.getProperty("jdbc.url");
		driver = properties.getProperty("jdbc.driver");
	}
	
	//单例模式获取JdbcUtils工具类
	public static JdbcUtils getJdbcUtils() {
		if (jdbcUtils == null) {
			synchronized (JdbcUtils.class) {
				if (jdbcUtils == null) {
					jdbcUtils = new JdbcUtils();
				}
			}
		}
		return jdbcUtils;

	}

	/**
	 * 获取Connection连接
	 */
	public static Connection getConnection() {
		// 建立与数据局之间的连接
		Connection conn = null;
		try {
			// 加载驱动
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 统一关闭资源
	 * 
	 */
	public static void freeAll(Connection conn, Statement st, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
