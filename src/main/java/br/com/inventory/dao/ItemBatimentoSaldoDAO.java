package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.batimento.ItemBatimentoSaldo;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;

public interface ItemBatimentoSaldoDAO {

	public void salvar(ItemBatimentoSaldo itemBatimento) throws DAOException;
	
	public ItemBatimentoSaldo buscarPorCodigo(Long codigo);
	
	public ItemBatimentoSaldo buscarPorProduto(Produto produto, Deposito deposito, BatimentoSaldo batimentoSaldo);
	
	public List<ItemBatimentoSaldo> listarPorBatimento(BatimentoSaldo batimentoSaldo);
	
}
