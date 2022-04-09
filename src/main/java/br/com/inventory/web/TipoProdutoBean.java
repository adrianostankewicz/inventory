package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.TipoProdutoRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.TipoProduto;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("tipoProdutoBean")
public class TipoProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private TipoProduto tipoProduto;
	
	@Inject
	private TipoProdutoRN tipoProdutoRN;
	
	public TipoProdutoBean(){
		tipoProduto = new TipoProduto();
	}

	public void salvar(){
		try{
			tipoProdutoRN.salvar(tipoProduto);
			tipoProduto = new TipoProduto();
			FacesUtil.addSuccessMessage("Tipo produto salvo com sucesso!");
			
		}catch(RNException rne){
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}
	
	public void salvarPorExcel(FileUploadEvent event){
		
		Excel excel = new Excel();
		
		try{
			excel.upload(event.getFile().getInputstream());
			tipoProdutoRN.salvarPorExcel(excel);
			
		} catch (IOException | UtilException | RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
