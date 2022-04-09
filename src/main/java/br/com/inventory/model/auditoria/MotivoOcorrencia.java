package br.com.inventory.model.auditoria;

public enum MotivoOcorrencia {

	INVERSAO("Inversão"),
	SOBRA("Sobra"),
	FALTA("Falta"),
	SINISTRO("Sinistro");
	
	private String motivo;
	
	MotivoOcorrencia(String motivo){
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}
}
