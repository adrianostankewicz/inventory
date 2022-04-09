package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.GrupoMercadoriaRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.GrupoMercadoria;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("grupoMercadoriaBean")
public class GrupoMercadoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private GrupoMercadoria grupoMercadoria;
	
	@Inject
	private GrupoMercadoriaRN grupoMercadoriaRN;
	
	public GrupoMercadoriaBean(){
		grupoMercadoria = new GrupoMercadoria();
	}
	
	public void salvar() {
		try {
			grupoMercadoriaRN.salvar(grupoMercadoria);
			grupoMercadoria = new GrupoMercadoria();
			FacesUtil.addSuccessMessage("Grupo de mercadoria salvo com sucesso.");
			
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event) {

		Excel excel = new Excel();
		
		try{
			excel.upload(event.getFile().getInputstream());
			grupoMercadoriaRN.salvarPorExcel(excel);
			
		} catch(IOException | UtilException | RNException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public GrupoMercadoria getGrupoMercadoria() {
		return grupoMercadoria;
	}
	public void setGrupoMercadoria(GrupoMercadoria grupoMercadoria) {
		this.grupoMercadoria = grupoMercadoria;
	}
}
