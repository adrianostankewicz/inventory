package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.ProdutoRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("produtoBean")
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	
	@Inject
	private ProdutoRN produtoRN;
	
	public ProdutoBean(){
		produto = new Produto();
	}

	public void salvar() {		
		try {
			produtoRN.salvar(produto);
			produto = new Produto();
			FacesUtil.addSuccessMessage("Produto salvo com sucesso.");
			
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event) {

		Excel excel = new Excel();
		
		try {
			excel.upload(event.getFile().getInputstream()); //Importa o arquivo Excel e abre a guia inicial.
			produtoRN.salvarPorExcel(excel); //Lê o arquivo e retorna uma lista com os produtos. Salva todos os produtos no banco de dados.
			
		} catch (RNException | IOException | UtilException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
