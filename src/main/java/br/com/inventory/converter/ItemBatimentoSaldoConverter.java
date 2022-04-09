package br.com.inventory.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.inventory.controller.ItemBatimentoSaldoRN;
import br.com.inventory.model.batimento.ItemBatimentoSaldo;

@FacesConverter(forClass = ItemBatimentoSaldo.class)
public class ItemBatimentoSaldoConverter implements Converter {

	@Inject
	private ItemBatimentoSaldoRN itemBatimentoSaldoRN;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ItemBatimentoSaldo ibs = null;
		
		if(value != null && !"".equals(value)){
			ibs = itemBatimentoSaldoRN.buscarPorCodigo(new Long(value));
		}
		return ibs;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((ItemBatimentoSaldo) value).getCodigo().toString();
		}
		return null;
	}

}
