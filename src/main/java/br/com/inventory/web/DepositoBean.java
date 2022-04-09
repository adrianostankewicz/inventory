package br.com.inventory.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.inventory.controller.DepositoRN;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;
import br.com.inventory.util.UtilException;

@ViewScoped
@Named("depositoBean")
public class DepositoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Deposito deposito;
	
	@Inject
	private DepositoRN depositoRN;
	
	public DepositoBean(){
		deposito = new Deposito();
	}
	
	public void salvar() {
		try {
			depositoRN.salvar(deposito);
			deposito = new Deposito();
			FacesUtil.addSuccessMessage("Depósito salvo com sucesso.");
			
		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void salvarPorExcel(FileUploadEvent event){

		Excel excel = new Excel();
	
		try {
			excel.upload(event.getFile().getInputstream());
			depositoRN.salvarPorExcel(excel);
			
		} catch (IOException | UtilException | RNException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
}
