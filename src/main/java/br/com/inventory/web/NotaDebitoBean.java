package br.com.inventory.web;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inventory.controller.NotaDebitoRN;
import br.com.inventory.model.auditoria.NotaDebito;
import br.com.inventory.model.auditoria.Responsavel;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

@ViewScoped
@Named("notaDebitoBean")
public class NotaDebitoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private NotaDebito notaDebito;
	
	@Inject
	private NotaDebitoRN notaDebitoRN;

	public void salvar() {
		try {
			notaDebitoRN.salvar(notaDebito);
			notaDebito = new NotaDebito();
			FacesUtil.addSuccessMessage("Nota de débito salvo com sucesso.");
				
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public NotaDebito getNotaDebito() {
		return notaDebito;
	}
	public void setNotaDebito(NotaDebito notaDebito) {
		this.notaDebito = notaDebito;
	}

	public Responsavel[] getResponsaveis() {
		return Responsavel.values();
	}
}
