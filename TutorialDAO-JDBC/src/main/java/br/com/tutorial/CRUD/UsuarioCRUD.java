package br.com.tutorial.CRUD;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioCRUD<Entidade> {

	//Por padrão é necessário criar uma interface onde passaremos a assinatura para depois criar a entidade DAO
	
	void salvar(Entidade usuario) throws SQLException;
	void editar(Entidade usuario) throws SQLException;
	void remover(Entidade usuario) throws SQLException;
	List<Entidade> listar();
	
}
