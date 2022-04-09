package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.Centro;
import br.com.inventory.util.DAOException;

public interface CentroDAO {

	public void salvar(Centro centro) throws DAOException;
	
	public Centro buscarPorCodigo(Long codigo);
	
	public Centro buscarPorDescricao(String descricao);
	
	public List<Centro> listar();
}
