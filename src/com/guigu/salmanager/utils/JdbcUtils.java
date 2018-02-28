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

	// �����û���
	private static final String username;
	// ��������
	private static final String password;
	// ����·��
	private static final String url;
	// ��������
	private static final String driver;

	private static JdbcUtils jdbcUtils;

	// ʹ�õ���ģʽ
	private JdbcUtils() {

	}

	static {
		Properties properties = new Properties();
		// ͨ��������� ��ָ�����ļ�ͨ��������ʽ���ؽ���
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
	
	//����ģʽ��ȡJdbcUtils������
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
	 * ��ȡConnection����
	 */
	public static Connection getConnection() {
		// ���������ݾ�֮�������
		Connection conn = null;
		try {
			// ��������
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
	 * ͳһ�ر���Դ
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
