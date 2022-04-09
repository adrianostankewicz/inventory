package br.com.inventory.model.auditoria;

public enum Responsavel {

	OPERADOR_LOGISTICO("Operador Logístico"),
	TRANSPORTES("Transportes"),
	OPERACOES_CURITIBA("Operações Curitiba"),
	DSR("DSR"),
	CLIENTE("Cliente");
	
	private String responsavel;
	
	Responsavel(String responsavel){
		this.responsavel = responsavel;
	}

	public String getResponsavel() {
		return responsavel;
	}
}
