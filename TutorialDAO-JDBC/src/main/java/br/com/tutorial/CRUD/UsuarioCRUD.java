package br.com.tutorial.CRUD;

import java.util.List;

public interface UsuarioCRUD<Entidade> {

	//Por padrão é necessário criar uma interface onde passaremos a assinatura para depois criar a entidade DAO
	
	void salvar(Entidade usuario);
	void editar(Entidade usuario);
	void remover(Entidade usuario);
	List<Entidade> listar();
	
}
