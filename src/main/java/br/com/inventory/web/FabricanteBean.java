package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.FabricanteRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.Fabricante;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("fabricanteBean")
public class FabricanteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Fabricante fabricante;
	
	@Inject
	private FabricanteRN fabricanteRN;
	
	public void salvar() {
		try{
			fabricanteRN.salvar(fabricante);
			fabricante = new Fabricante();
			FacesUtil.addSuccessMessage("Fabricante salvo com sucesso!");
				
		}catch(RNException rne){
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event) {

		Excel excel = new Excel();

		try{
			excel.upload(event.getFile().getInputstream());
			fabricanteRN.salvarPorExcel(excel);

		} catch(IOException | UtilException | RNException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public FabricanteBean(){
		fabricante = new Fabricante();
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
}
