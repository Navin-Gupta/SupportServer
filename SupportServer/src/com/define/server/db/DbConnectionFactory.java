package com.define.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnectionFactory {

	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/supportdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	// 1. method for DMQ (insert, update, delete)
	public int updateData(String sql) throws Exception{
		Class.forName(DRIVER_CLASS);
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Statement stmt = con.createStatement();
		int n = stmt.executeUpdate(sql);
		return n;
	}
	
	
	// 2. method for select (Connection Oriented)
	public ResultSet getConnData(String sql) throws Exception{
		Class.forName(DRIVER_CLASS);
		Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	// 3. method for select (Disconnected)
	
}
