package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.EnderecoRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("enderecoBean")
public class EnderecoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Endereco endereco;
	
	@Inject
	private EnderecoRN enderecoRN;

	public EnderecoBean(){
		endereco = new Endereco();
	}
	
	public void salvar() {
		try {
			enderecoRN.salvar(endereco);
			endereco = new Endereco();
			FacesUtil.addSuccessMessage("Endereço salvo com sucesso.");
				
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event){

		Excel excel = new Excel();

		try {
			excel.upload(event.getFile().getInputstream());
			enderecoRN.salvarPorExcel(excel);
			
		} catch (RNException | IOException | UtilException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
