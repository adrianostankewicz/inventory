package br.com.inventory.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inventory.kpi.Chart;
import br.com.inventory.kpi.EstoqueKPI;
import br.com.inventory.model.estoque.ItemEstoque;

@Named
@ViewScoped
public class DashboardBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueKPI estoqueKPI;
	private Chart chartEstoqueValor;
	
	public DashboardBean(){
		chartEstoqueValor = new Chart();
	}
	
	public Chart getChartEstoqueValor() {
		chartEstoqueValor.criarPieChart(estoqueKPI.getValorTotalPorCentro(), "Total em R$ de estoque por centro");
		return chartEstoqueValor;
	}
	
	public List<ItemEstoque> getSaldoPorDeposito(){
		return null;
	}
}
