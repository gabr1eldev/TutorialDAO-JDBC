package br.com.tutorial.util;

import java.sql.Connection;

import br.com.tutorial.connection.ConnectionBase;

public class ConnectionTest {

	// Testando se a conexão com o banco está funcionando

	public static void main(String[] args) {
		Connection conexao = ConnectionBase.getConnection();
		System.out.println(conexao);
	}

}
