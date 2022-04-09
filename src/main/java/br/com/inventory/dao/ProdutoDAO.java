package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;

public interface ProdutoDAO {

	public void salvar(Produto produto) throws DAOException;
	
	public Produto buscarPorCodigo(Long codigo);
	
	public Produto buscarPorCodigoSAP(Integer codigoSAP);
	
	public List<Produto> listar();
}
