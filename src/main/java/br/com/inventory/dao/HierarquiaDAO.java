package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.produto.Hierarquia;
import br.com.inventory.util.DAOException;

public interface HierarquiaDAO {

	public void salvar(Hierarquia hierarquia) throws DAOException;
	
	public Hierarquia buscarPorCodigo(Long codigo);
	
	public Hierarquia buscarPorCodigoSAP(String codigoSAP);
	
	public Hierarquia buscarPorDescricao(String descricao);
	
	public List<Hierarquia> listar();
}
