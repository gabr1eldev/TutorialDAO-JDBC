package br.com.tutorial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import br.com.tutorial.CRUD.UsuarioCRUD;
import br.com.tutorial.connection.ConnectionBase;
import br.com.tutorial.entity.Usuario;

public class UsuarioDAO implements UsuarioCRUD<Usuario> {

	@Override
	public void salvar(Usuario usuario) throws SQLException {
		// criamos uma variavel do tipo connection como null,caso uma conexão já esteja
		// aberta ela irá iniciar como null
		Connection conexao = null;
		PreparedStatement pst = null;

		// no jdbc nós temos total controle do que fazemos no DAO,aqui é bom deixar o
		// autoCommit false e fazer o commit manualmente
		try {
			conexao = ConnectionBase.getConnection();
			conexao.setAutoCommit(false);
			String sql = "INSERT INTO usuario(nome,login,senha) VALUES(?,?,?)";
			pst = conexao.prepareStatement(sql);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getLogin());
			pst.setString(3, usuario.getSenha());

			pst.execute();

			// aqui iremos declarar o rollback caso aconteça algum erro
		} catch (Exception e) {
			e.printStackTrace();
			conexao.rollback();

			// por mim iremos dar commit e fechar todas as conexões
		} finally {
			conexao.commit();
			pst.close();
			conexao.close();
		}

	}

	// o salvar,editar e remover,particularmente são a mesma coisa

	@Override
	public void editar(Usuario usuario) throws SQLException {
		Connection conexao = null;
		PreparedStatement pst = null;

		try {
			conexao = ConnectionBase.getConnection();
			conexao.setAutoCommit(false);

			String query = "UPDATE usuario SET nome=?, login?, senha? WHERE login=? ";
			pst = conexao.prepareStatement(query);

			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getLogin());
			pst.setString(3, usuario.getSenha());

			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
			conexao.rollback();
		} finally {
			conexao.commit();
			pst.close();
			conexao.close();
		}
	}

	@Override
	public void remover(Usuario usuario) throws SQLException {
		Connection conexao = null;
		PreparedStatement pst = null;

		try {
			conexao = ConnectionBase.getConnection();
			conexao.setAutoCommit(false);
			String query = "DELETE FROM usuario WHERE login=?";
			pst = conexao.prepareStatement(query);

			pst.setString(1, usuario.getLogin());
			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
			conexao.rollback();
		} finally {
			conexao.commit();
			pst.close();
			conexao.close();
		}

	}

	// Aqui no listar você não poe conexão null pois para listar é porque vc já está
	// dentro de uma conexão
	@Override
	public List<Usuario> listar() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		Connection conexao = ConnectionBase.getConnection();
		Statement st = null;

		try {
			String select = "SELECT u.nome,u.login,u.senha FROM Usuario u";
			st = conexao.createStatement();

			// o resultSet serve para armazenar os dados do banco em uma estrutura de dados
			// que pode ser percorrida e exibida de forma que possa ler os dados
			ResultSet result = st.executeQuery(select);
			while (result.next()) {
				Usuario usuario = new Usuario(result.getString("nome"), result.getString("login"),
						result.getString("senha"));
				listaUsuario.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaUsuario;
	}

}
