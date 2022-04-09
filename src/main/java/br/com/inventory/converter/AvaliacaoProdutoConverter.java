package br.com.inventory.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.com.inventory.controller.AvaliacaoProdutoRN;
import br.com.inventory.model.produto.AvaliacaoProduto;

public class AvaliacaoProdutoConverter implements Converter {

	@Inject
	private AvaliacaoProdutoRN avaliacaoProdutoRN;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		AvaliacaoProduto avaliacaoProduto = null;

		if (value != null && !"".equals(value)) {
			avaliacaoProduto = avaliacaoProdutoRN.buscarPorCodigo(new Long(value));
		}
		return avaliacaoProduto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null) {
			return ((AvaliacaoProduto) value).getCodigo().toString();
		}
		return null;
	}

}
