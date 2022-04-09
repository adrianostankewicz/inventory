package br.com.inventory.model.auditoria;

public enum MotivoItemOcorrencia {

	FALTANDO("Faltando"),
	SOBRANDO("Sobrando");
	
	private String descricao;
	
	MotivoItemOcorrencia(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
