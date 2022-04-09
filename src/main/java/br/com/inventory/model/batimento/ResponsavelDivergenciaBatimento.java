package br.com.inventory.model.batimento;

public enum ResponsavelDivergenciaBatimento {

	OFFICER_FATURAMENTO("Officer faturamento"),
	OFFICER_RECEBIMENTO("Officer recebimento"),
	OFFICER_REVERSA("Officer reversa"),
	OFFICER_INVENTARIO("Officer inventario"),
	OFFICER_SUPORTE("Officer suporte"),
	OFFICER_OUTROS("Officer outros"),
	OPL_FATURAMENTO("Opl faturamento"),
	OPL_RECEBIMENTO("Opl recebimento"),
	OPL_REVERSA("Opl reversa"),
	OPL_INVENTARIO("Opl inventario"),
	OPL_SUPORTE("Opl suporte"),
	OPL_OUTROS("Opl outros"),
	INICIAL("");
	
	private String responsavel;
	
	ResponsavelDivergenciaBatimento(String responsavel){
		this.responsavel = responsavel;
	}
	
	public String getResponsavel(){
		return responsavel;
	}
}
