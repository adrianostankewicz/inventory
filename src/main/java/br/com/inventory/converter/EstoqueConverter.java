package br.com.inventory.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.inventory.controller.EstoqueRN;
import br.com.inventory.model.estoque.Estoque;

@FacesConverter(forClass = Estoque.class)
public class EstoqueConverter implements Converter {

	@Inject
	private EstoqueRN estoqueRN;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estoque estoque = null;
		
		if(value != null && !"".equals(value)){
			
			estoque = estoqueRN.buscarPorCodigo(new Long(value));
		}
		return estoque;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			return ((Estoque) value).getCodigo().toString();
		}
		return null;
	}
	
}

