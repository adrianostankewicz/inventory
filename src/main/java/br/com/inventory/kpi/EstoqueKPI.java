package br.com.inventory.kpi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import br.com.inventory.controller.EstoqueRN;
import br.com.inventory.controller.ItemEstoqueRN;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.estoque.ItemEstoque;

public class EstoqueKPI implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueRN estoqueRN;
	
	@Inject
	private ItemEstoqueRN itemEstoqueRN;
	
	public Map<String, Double> getValorTotalPorCentro(){
			
		Map<String, Double> totalCentro = new HashMap<>(); 
		
		for(Estoque estoque : estoqueRN.listar()){
			Double valorTotal = 0.0;
			for(ItemEstoque item : itemEstoqueRN.listarPorEstoque(estoque)){
				valorTotal += item.getValorUnitario() * item.getSaldo();
			}
			
			totalCentro.put(estoque.getCentro().getDescricao(), valorTotal);
		}
		return totalCentro;
	}
	
}
