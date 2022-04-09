package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.Centro;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.util.DAOException;

public interface EstoqueDAO {

	public void salvar(Estoque estoque) throws DAOException;
	
	public Estoque buscarPorCodigo(Long codigo);
	
	public Estoque buscarPorCentro(Centro centro);
	
	public List<Estoque> listar();
}
