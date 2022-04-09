package br.com.inventory.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.inventory.dao.ItemBatimentoSaldoDAO;
import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.batimento.ItemBatimentoSaldo;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class ItemBatimentoSaldoRN implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemBatimentoSaldoDAO itemBatimentoSaldoDAO;
	
	public void salvar(ItemBatimentoSaldo itemBatimento) throws RNException{
		try {
			itemBatimentoSaldoDAO.salvar(itemBatimento);
			
		} catch (DAOException de) {
			throw new RNException(de.getMessage());
		}
	}
	
	public ItemBatimentoSaldo buscarPorCodigo(Long codigo){
		return itemBatimentoSaldoDAO.buscarPorCodigo(codigo);
	}
	
	public ItemBatimentoSaldo buscarPorProduto(Produto produto, Deposito deposito, BatimentoSaldo batimentoSaldo){
		return itemBatimentoSaldoDAO.buscarPorProduto(produto, deposito, batimentoSaldo);
	}
	
	public List<ItemBatimentoSaldo> listarPorBatimento(BatimentoSaldo batimentoSaldo){
		return itemBatimentoSaldoDAO.listarPorBatimento(batimentoSaldo);
	}
}
