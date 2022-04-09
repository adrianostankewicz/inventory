package br.com.inventory.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.inventory.dao.BatimentoSaldoDAO;
import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.batimento.ItemBatimento;
import br.com.inventory.model.batimento.ItemBatimentoSaldo;
import br.com.inventory.model.batimento.StatusItemBatimento;
import br.com.inventory.model.estoque.Enderecamento;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.estoque.ItemEstoque;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class BatimentoSaldoRN implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private BatimentoSaldoDAO batimentoSaldoDAO;

	@Inject
	private ItemEstoqueRN itemEstoqueRN;

	@Inject
	private EnderecamentoRN enderecamentoRN;

	private List<ItemBatimentoSaldo> itensBatimentoSaldo;

	public BatimentoSaldoRN() {
		itensBatimentoSaldo = new ArrayList<ItemBatimentoSaldo>();
	}

	public void salvar(BatimentoSaldo batimentoSaldo) throws RNException {
		
		try {
			if(batimentoSaldoDAO.buscarPorDataRealizado(batimentoSaldo.getDataRealizado(),
					batimentoSaldo.getEstoque()) == null){
				batimentoSaldoDAO.salvar(batimentoSaldo);
			}

		} catch (DAOException de) {
			throw new RNException(de.getMessage());
		}
	}

	public List<ItemBatimentoSaldo> gerar(Estoque estoque) {

		List<ItemEstoque> itensEstoque = itemEstoqueRN.consolidarSaldoPorProduto(estoque);
		List<Enderecamento> itensEndereco = enderecamentoRN.consolidarSaldoPorProduto(estoque);

		listarDivergencias(itensEstoque, itensEndereco);

		return itensBatimentoSaldo;
	}

	/**
	 * Método responsável por listar as divergências entre o estoque contábil e
	 * físico.
	 * 
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param List<ItemEstoque>
	 * @param List<Enderecamento>
	 */
	public void listarDivergencias(List<ItemEstoque> itensEstoque, List<Enderecamento> itensEndereco) {

		boolean encontrou;

		for (ItemEstoque ie : itensEstoque) {
			encontrou = false;
			for (Enderecamento end : itensEndereco) {
				if (ie.getProduto().equals(end.getProduto())) {
					if (ie.getDeposito().equals(end.getDeposito())) {
						
						encontrou = true;
						
						if ((ie.getSaldo() - end.getSaldo()) != 0) {
							adicionarItem(
									new ItemBatimento(ie.getProduto(), ie.getDeposito(), ie.getSaldo(), end.getSaldo()));
							break;
						}
					}
				}
			}

			if (!encontrou) {
				adicionarItem(new ItemBatimento(ie.getProduto(), ie.getDeposito(), ie.getSaldo(), 0));
			}
		}

		for (Enderecamento end : itensEndereco) {
			encontrou = false;
			for (ItemEstoque ie : itensEstoque) {
				if (end.getProduto().equals(ie.getProduto())) {
					if (end.getDeposito().equals(ie.getDeposito())) {
						encontrou = true;
						break;
					}
				}
			}

			if (!encontrou) {
				adicionarItem(new ItemBatimento(end.getProduto(), end.getDeposito(), 0, end.getSaldo()));
			}
		}
	}

	/**
	 * Método responsável por adicionar um item a lista de divergências entre o
	 * estoque contábil e físico.
	 * 
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param List<ItemBatimento>
	 */
	public void adicionarItem(ItemBatimento ib) {

		ItemBatimentoSaldo ibs = new ItemBatimentoSaldo();
		ibs.setDeposito(ib.getDeposito());
		ibs.setProduto(ib.getProduto());
		ibs.setAjuste(0);
		ibs.setJustificativa("");

		ibs.setSaldoEstoque((ib.getSaldoEstoque()));
		ibs.setSaldoEndereco(ib.getSaldoEndereco());
		ibs.calcularDivergencia();
		ibs.calcularDivergenciaFinal();
		ibs.setStatusItem(StatusItemBatimento.DIVERGENTE);

		itensBatimentoSaldo.add(ibs);
	}

	public BatimentoSaldo buscarPorCodigo(Long codigo) {
		return batimentoSaldoDAO.buscarPorCodigo(codigo);
	}

	public BatimentoSaldo buscarPorDataRealizado(Date dataRealizado, Estoque estoque) {
		return batimentoSaldoDAO.buscarPorDataRealizado(dataRealizado, estoque);
	}

	public List<BatimentoSaldo> listarPorEstoque(Date inicio, Date fim, Estoque estoque) {
		return batimentoSaldoDAO.listarPorEstoque(inicio, fim, estoque);
	}

	public List<BatimentoSaldo> listarPorData(Date inicio, Date fim) {
		return batimentoSaldoDAO.listarPorData(inicio, fim);
	}
}
