package br.com.tutorial.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBase {
	
	//Criando a conexão JDBC
	
	public static Connection getConnection() {
		Connection conexao = null;

		try {
			String sql = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "greg";
			String password = "12345";

			Class.forName(sql);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
