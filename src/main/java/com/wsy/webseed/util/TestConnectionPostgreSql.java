package com.wsy.webseed.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionPostgreSql {
	private Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/geely";
			conn = DriverManager.getConnection(url, "wangsy", "123456");
			System.out.println(conn.getCatalog());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(2);
		}
		return conn;
	}
	
	public static void main(String[] args) {
		TestConnectionPostgreSql clazz = new TestConnectionPostgreSql();
		clazz.connect();
	}
}
