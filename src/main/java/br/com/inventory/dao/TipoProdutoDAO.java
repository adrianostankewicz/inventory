package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.produto.TipoProduto;
import br.com.inventory.util.DAOException;

public interface TipoProdutoDAO {

	public void salvar(TipoProduto tipoProduto) throws DAOException;
	
	public TipoProduto buscarPorCodigo(Long codigo);
	
	public TipoProduto buscarPorCodigoSAP(String codigoSAP);
	
	public List<TipoProduto> listar();
}
