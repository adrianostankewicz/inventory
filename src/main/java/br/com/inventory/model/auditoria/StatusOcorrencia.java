package br.com.inventory.model.auditoria;

public enum StatusOcorrencia {

	AGUARDANDO_DEVOLUCAO("Aguardando devolução"),
	RECEBIDO("Recebido"),
	FINALIZADO("Finalizado");
	
	private String status;
	
	StatusOcorrencia(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
