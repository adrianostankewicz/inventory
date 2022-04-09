package br.com.inventory.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.inventory.controller.BatimentoSaldoRN;
import br.com.inventory.model.batimento.BatimentoSaldo;

@FacesConverter(forClass = BatimentoSaldo.class)
public class BatimentoSaldoConverter implements Converter {

	@Inject
	private BatimentoSaldoRN batimentoSaldoRN;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		BatimentoSaldo batimentoSaldo = null;
		
		if(value != null && !"".equals(value)){
			batimentoSaldo = batimentoSaldoRN.buscarPorCodigo(new Long(value));
		}
		return batimentoSaldo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Long codigo = ((BatimentoSaldo) value).getCodigo(); 
			String retorno = (codigo == null ? null : codigo.toString());
			return retorno;
		}
		return "";
	}
	
}

