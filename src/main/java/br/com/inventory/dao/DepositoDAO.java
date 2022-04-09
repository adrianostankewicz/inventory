package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.util.DAOException;

public interface DepositoDAO {

	public void salvar(Deposito deposito) throws DAOException;
	
	public Deposito buscarPorCodigo(Long codigo);
	
	public Deposito buscarPorDescricao(String descricao);
	
	public List<Deposito> listar();
}
