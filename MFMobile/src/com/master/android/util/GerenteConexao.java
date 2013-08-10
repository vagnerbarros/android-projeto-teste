package com.master.android.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenteConexao {

	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://";
	public static String endereco = "192.168.100.241/masterfila";
	public static String user = "vagner";
	public static String passw = "26289214";
	static Connection con;

	private GerenteConexao(){
		
	}
	
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url + endereco, user, passw);
		return con;
	}
}
