package com.louishong.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class SQliteBase {
	public static String sDriver;
	public static String sUrl;
	public static Connection con;
	public static ResultSet result = null;

	public void setDriver(String driver) {
		// Register Driver #1
		try {
			Driver d = (Driver) Class.forName(driver).newInstance();
			DriverManager.registerDriver(d);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createConnection() {
		try {
			con = DriverManager.getConnection(sUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

	}

	public ResultSet executeQuery(String sql) {

		// Execute SQL Query using Statement object
		Statement stmt;
		try {
			stmt = con.createStatement();
			result = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;

	}

	public SQliteBase(String stringDriver, String stringUrl) {

		sDriver = stringDriver;
		sUrl = stringUrl;

		// Register Driver #1
		setDriver(sDriver);

		// Create Connection
		createConnection();

	}

}
