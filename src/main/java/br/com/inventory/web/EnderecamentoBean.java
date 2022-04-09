package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.EnderecamentoRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Enderecamento;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named
public class EnderecamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Enderecamento enderecamento;
	
	@Inject
	private EnderecamentoRN enderecamentoRN;
	
	public EnderecamentoBean(){
		enderecamento = new Enderecamento();
	}

	public void salvarPorExcel(FileUploadEvent event){
		
		Excel excel = new Excel();
		
		try{
			excel.upload(event.getFile().getInputstream());
			enderecamentoRN.salvarPorExcel(excel);
			
		} catch (IOException | UtilException | RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Enderecamento getEnderecamento() {
		return enderecamento;
	}
	public void setEnderecamento(Enderecamento enderecamento) {
		this.enderecamento = enderecamento;
	}
}
