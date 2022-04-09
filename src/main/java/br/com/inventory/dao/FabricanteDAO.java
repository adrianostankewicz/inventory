package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.Fabricante;
import br.com.inventory.util.DAOException;

public interface FabricanteDAO {

	public void salvar(Fabricante fabricante) throws DAOException;
	
	public Fabricante buscarPorCodigo(Long codigo);
	
	public Fabricante buscarPorNome(String nome);
	
	public List<Fabricante> listar();
	
}
