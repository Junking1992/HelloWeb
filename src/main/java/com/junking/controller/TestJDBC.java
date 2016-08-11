package com.junking.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysql";
		String username = "root";
		String password = "wj8621174";
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
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(getConnection());
	}

}
