package br.com.inventory.kpi;

import java.io.Serializable;
import java.util.Map;

import org.primefaces.model.chart.PieChartModel;

public class Chart implements Serializable {
	private static final long serialVersionUID = 1L;

	private PieChartModel pieChart;
	
	public void criarPieChart(Map<String, Double> guidelines, String title){
		pieChart = new PieChartModel();
		pieChart.setTitle(title);
		pieChart.setLegendPosition("w");
		
		for(String key : guidelines.keySet()){
			pieChart.set(key, guidelines.get(key));
		}
	}
	
	public PieChartModel getPieChart() {
		return pieChart;
	}
}
