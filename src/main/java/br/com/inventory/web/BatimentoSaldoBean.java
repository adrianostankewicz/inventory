package br.com.inventory.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.com.inventory.controller.BatimentoSaldoRN;
import br.com.inventory.controller.EstoqueRN;
import br.com.inventory.controller.ItemBatimentoSaldoRN;
import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.batimento.ItemBatimentoSaldo;
import br.com.inventory.model.batimento.MotivoDivergenciaBatimento;
import br.com.inventory.model.batimento.ResponsavelDivergenciaBatimento;
import br.com.inventory.model.batimento.StatusBatimento;
import br.com.inventory.model.batimento.StatusItemBatimento;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

@ViewScoped
@Named("batimentoSaldoBean")
public class BatimentoSaldoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BatimentoSaldo batimentoSaldo;
	
	@Inject
	private BatimentoSaldoRN batimentoSaldoRN;
	
	@Inject 
	private EstoqueRN estoqueRN;
	
	@Inject
	private ItemBatimentoSaldoRN itemBatimentoSaldoRN;
	
	private List<ItemBatimentoSaldo> itensBatimentoSaldo;
	private List<ItemBatimentoSaldo> itensBatimentoFiltrados;
	
	public BatimentoSaldoBean(){
		limpar();
	}
	
	public void inicializar(){
		if(batimentoSaldo == null){
			limpar();
		}
	}

	public void salvar() {
		try {
			batimentoSaldoRN.salvar(batimentoSaldo);
			this.salvarTodosItens();
			
		} catch (RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void gerar() {	
		batimentoSaldo.setStatus(StatusBatimento.EM_ANALISE);
		itensBatimentoSaldo = batimentoSaldoRN.gerar(batimentoSaldo.getEstoque());
	}
	
	public void salvarItem(ItemBatimentoSaldo ibs){
		
		batimentoSaldo = batimentoSaldoRN.buscarPorDataRealizado(batimentoSaldo.getDataRealizado(), batimentoSaldo.getEstoque());
		ibs.setBatimentoSaldo(batimentoSaldo);
		
		try {
			itemBatimentoSaldoRN.salvar(ibs);
				
		} catch (RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void salvarTodosItens(){

		for (ItemBatimentoSaldo ibs : itensBatimentoSaldo) {
			this.salvarItem(ibs);
		}
	}

	public void onRowEdit(RowEditEvent event) {
		ItemBatimentoSaldo item = (ItemBatimentoSaldo) event.getObject();
		item.ajustarDivergencia();
		
		int indice = itensBatimentoSaldo.indexOf(item);
		itensBatimentoSaldo.set(indice, item);
	}
	
	public void limpar(){
		batimentoSaldo = new BatimentoSaldo();
		batimentoSaldo.setStatus(StatusBatimento.INICIAL);
		batimentoSaldo.setDataRealizado(new Date());
		batimentoSaldo.setHoraRealizado(new Date());
		
		itensBatimentoSaldo = new ArrayList<ItemBatimentoSaldo>();
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public BatimentoSaldo getBatimentoSaldo() {
		return batimentoSaldo;
	}
	public void setBatimentoSaldo(BatimentoSaldo batimentoSaldo) {
		this.batimentoSaldo = batimentoSaldo;
	}
	
	public List<ItemBatimentoSaldo> getItensBatimentoSaldo() {
		return itensBatimentoSaldo;
	}

	public List<ItemBatimentoSaldo> getItensBatimentoFiltrados() {
		return itensBatimentoFiltrados;
	}

	public ResponsavelDivergenciaBatimento[] getResponsaveis() {
		return ResponsavelDivergenciaBatimento.values();
	}

	public StatusBatimento[] getStatusBatimento() {
		return StatusBatimento.values();
	}

	public StatusItemBatimento[] getStatusItemBatimento() {
		return StatusItemBatimento.values();
	}

	public MotivoDivergenciaBatimento[] getMotivos() {
		return MotivoDivergenciaBatimento.values();
	}

	public List<Estoque> getEstoques(){
		return estoqueRN.listar();
	}
	
}
