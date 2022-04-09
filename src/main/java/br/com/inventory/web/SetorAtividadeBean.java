package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.SetorAtividadeRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.SetorAtividade;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named
public class SetorAtividadeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private SetorAtividade setorAtividade;
	
	@Inject
	private SetorAtividadeRN setorAtividadeRN;
	
	public SetorAtividadeBean(){
		setorAtividade = new SetorAtividade();
	}

	public void salvar(){
		try{
			setorAtividadeRN.salvar(setorAtividade);
			setorAtividade = new SetorAtividade(); 
			FacesUtil.addSuccessMessage("Setor de atividade salvo com sucesso!");
		
		} catch(RNException rne){
			FacesUtil.addErrorMessage(rne.getMessage());
		} 
	}
	
	public void salvarPorExcel(FileUploadEvent event){
		
		Excel excel = new Excel();
		
		try{
			excel.upload(event.getFile().getInputstream());
			setorAtividadeRN.salvarPorExcel(excel);
			
		} catch (IOException | UtilException | RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public SetorAtividade getSetorAtividade() {
		return setorAtividade;
	}
	public void setSetorAtividade(SetorAtividade setorAtividade) {
		this.setorAtividade = setorAtividade;
	}	
}
