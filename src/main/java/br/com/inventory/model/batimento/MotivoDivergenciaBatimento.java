package br.com.inventory.model.batimento;

public enum MotivoDivergenciaBatimento {

	PENDENCIA_FATURAMENTO("Pendência de Faturamento"),
	PENDENCIA_RECEBIMENTO("Pendência de Recebimento"),
	PENDENCIA_AJUSTE("Pendência de ajuste"),
	RECEBIMENTO_ANTECIPADO("Recebimento antecipado"),
	FATURAMENTO_ANTECIPADO("Faturamento antecipado"),
	MATERIAL_NAO_LOCALIZADO("Material não localizado"),
	INICIAL("");
	
	private String motivo;
	
	MotivoDivergenciaBatimento(String motivo){
		this.motivo = motivo;
	}
	
	public String getMotivo(){
		return motivo;
	}
}
