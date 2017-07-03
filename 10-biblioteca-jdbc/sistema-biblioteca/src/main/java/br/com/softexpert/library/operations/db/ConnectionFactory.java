package br.com.softexpert.library.operations.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
	     try {
	         return DriverManager.getConnection(	
	        "jdbc:jtds:sqlserver://localhost:1433/db_library","sa","Soft#2017");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
}

