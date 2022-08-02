package com.caionilson.core.tema10;

import java.sql.*;

public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tema10";
	private static final String USER = "root";
	private static final String PASS = "muitosecreta";

	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException("Erro na conexao", e);
		}
	}
	
	public void closeConnection(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
