package br.com.inventory.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.inventory.dao.ItemEstoqueDAO;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.estoque.ItemEstoque;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class ItemEstoqueRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemEstoqueDAO itemEstoqueDAO;
	
	public void salvar(ItemEstoque itemEstoque) throws RNException{
		try{
			itemEstoqueDAO.salvar(itemEstoque);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public ItemEstoque buscarPorCodigo(Long codigo){
		return itemEstoqueDAO.buscarPorCodigo(codigo);
	}
	
	public ItemEstoque buscarPorProduto(Produto produto, Deposito deposito){
		return itemEstoqueDAO.buscarPorProduto(produto, deposito);
	}
	
	public List<ItemEstoque> consolidarSaldoPorProduto(Estoque estoque){
		return itemEstoqueDAO.consolidarSaldoPorProduto(estoque);
	}
	
	public List<ItemEstoque> listarPorEstoque(Estoque estoque){
		return itemEstoqueDAO.listarPorEstoque(estoque);
	}
	
	public List<ItemEstoque> listar(){
		return itemEstoqueDAO.listar();
	}
	
	public void limpar(){
		itemEstoqueDAO.limpar();
	}
}
