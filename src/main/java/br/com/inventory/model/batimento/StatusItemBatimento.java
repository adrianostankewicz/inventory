package br.com.inventory.model.batimento;

public enum StatusItemBatimento {

	DIVERGENTE("Divergente"),
	OK("OK");
	
	
	private String status;
	
	StatusItemBatimento(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
}
