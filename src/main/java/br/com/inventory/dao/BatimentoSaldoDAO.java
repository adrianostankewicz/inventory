package br.com.inventory.dao;

import java.util.Date;
import java.util.List;

import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.util.DAOException;

public interface BatimentoSaldoDAO {

	public void salvar(BatimentoSaldo batimentoSaldo) throws DAOException;
	
	public BatimentoSaldo buscarPorCodigo(Long codigo);
	
	public BatimentoSaldo buscarPorDataRealizado(Date dataRealizado, Estoque estoque);
	
	public List<BatimentoSaldo> listarPorEstoque(Date inicio, Date fim, Estoque estoque);
	
	public List<BatimentoSaldo> listarPorData(Date inicio, Date fim);
}
