package com.junking.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		String driver = "COM.ibm.db2.jdbc.app.DB2Driver";
		String url = "jdbc:db2:TEST";
		String username = "db2admin";
		String password = "db2admin";
		Class.forName(driver);
		return (Connection) DriverManager.getConnection(url, username, password);
	}
	
	public void create(){
		
	}
	
	public void delete(){
		
	}
	
	public void update(){
		
	}
	
	public void query(){

	}
	
	public static void main(String[] args) {
		
	}

}
