package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import br.com.inventory.controller.EstoqueRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@RequestScoped
@Named("estoqueBean")
public class EstoqueBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Estoque estoque;
	
	@Inject
	private EstoqueRN estoqueRN;
	
	private Excel excel;
	
	private StreamedContent file;
	
	public EstoqueBean(){
		excel = new Excel();
	}

	public void salvarPorExcel(FileUploadEvent event){
		
		try{
			excel.upload(event.getFile().getInputstream());
			estoqueRN.salvarPorExcel(excel);
			excel.close();

		} catch(IOException | UtilException | RNException e){
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public StreamedContent getFile() {
		return file;
	}
}
