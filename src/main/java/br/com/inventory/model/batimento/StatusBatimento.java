package br.com.inventory.model.batimento;

public enum StatusBatimento {

	FINALIZADO("Finalizado"),
	EM_ANALISE("Em análise"),
	INICIAL("Inicial");
	
	private String status;
	
	StatusBatimento(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
}
