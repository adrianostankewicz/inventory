package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.HierarquiaRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.Hierarquia;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("hierarquiaBean")
public class HierarquiaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Hierarquia hierarquia;
	
	@Inject
	private HierarquiaRN hierarquiaRN;
	
	public HierarquiaBean(){
		hierarquia = new Hierarquia();
	}
	
	public void salvar() {
		try {
			hierarquiaRN.salvar(hierarquia);
			hierarquia = new Hierarquia();
			FacesUtil.addSuccessMessage("Hierarquia salvo com sucesso.");
			
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event) {
		
		Excel excel = new Excel();

		try{
			excel.upload(event.getFile().getInputstream());
			hierarquiaRN.salvarPorExcel(excel);
			
		} catch(IOException | UtilException | RNException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Hierarquia getHierarquia() {
		return hierarquia;
	}
	public void setHierarquia(Hierarquia hierarquia) {
		this.hierarquia = hierarquia;
	}
}
