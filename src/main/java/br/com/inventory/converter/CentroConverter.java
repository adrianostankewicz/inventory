package br.com.inventory.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.inventory.controller.CentroRN;
import br.com.inventory.model.Centro;

@FacesConverter(forClass = Centro.class)
public class CentroConverter implements Converter {

	@Inject
	private CentroRN centroRN;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Centro centro = null;

		if (value != null && !"".equals(value)) {
			centro = centroRN.buscarPorCodigo(new Long(value));
		}
		return centro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			return ((Centro) value).getCodigo().toString();
		}
		return "";
	}

}
