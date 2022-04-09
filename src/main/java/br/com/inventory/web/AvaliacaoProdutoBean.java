package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.AvaliacaoProdutoRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.AvaliacaoProduto;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("avaliacaoProdutoBean")
public class AvaliacaoProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private AvaliacaoProduto avaliacaoProduto;
	
	@Inject
	private AvaliacaoProdutoRN avaliacaoProdutoRN;

	public AvaliacaoProdutoBean(){
		this.avaliacaoProduto = new AvaliacaoProduto();
	}
	
	public void salvar() {		
		try {
			avaliacaoProdutoRN.salvar(avaliacaoProduto);
			avaliacaoProduto = new AvaliacaoProduto();
			FacesUtil.addSuccessMessage("Avaliação de produto salvo com sucesso!");
			
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event) {
		
		Excel excel = new Excel();
		
		try {
			excel.upload(event.getFile().getInputstream());
			avaliacaoProdutoRN.salvarPorExcel(excel);
			
		} catch (IOException | UtilException | RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public AvaliacaoProduto getAvaliacaoProduto() {
		return avaliacaoProduto;
	}
	public void setAvaliacaoProduto(AvaliacaoProduto avaliacaoProduto) {
		this.avaliacaoProduto = avaliacaoProduto;
	}
	
	public void limpar(){
		avaliacaoProduto = new AvaliacaoProduto();
	}
}
