package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.CentroRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.Centro;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("centroBean")
public class CentroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Centro centro;
	
	@Inject
	private CentroRN centroRN;

	public CentroBean(){
		this.centro = new Centro();
	}
	
	public void salvar(){
		try {
			centroRN.salvar(centro);
			centro = new Centro();
			FacesUtil.addSuccessMessage("Centro salvo com sucesso.");
			
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}
	
	public void salvarPorExcel(FileUploadEvent event) {

		Excel excel = new Excel();
		
		try {
			excel.upload(event.getFile().getInputstream());
			centroRN.salvarPorExcel(excel);

		} catch (IOException | UtilException | RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
}
