package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.estoque.ItemEstoque;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;

public interface ItemEstoqueDAO {

	public void salvar(ItemEstoque itemEstoque) throws DAOException;
	
	public ItemEstoque buscarPorCodigo(Long codigo);
	
	public ItemEstoque buscarPorProduto(Produto produto, Deposito deposito);
	
	public List<ItemEstoque> consolidarSaldoPorProduto(Estoque estoque);
	
	public List<ItemEstoque> listarPorEstoque(Estoque estoque);
	
	public List<ItemEstoque> listar();
	
	public void limpar();
	
}
