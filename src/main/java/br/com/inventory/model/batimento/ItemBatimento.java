package br.com.inventory.model.batimento;

import java.io.Serializable;

import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.produto.Produto;

public class ItemBatimento implements Serializable{
	private static final long serialVersionUID = 1L;

	private Deposito deposito;
	private Produto produto;
	private Integer saldoEstoque;
	private Integer saldoEndereco;
	
	public ItemBatimento(Produto produto, Deposito deposito, Integer saldoEstoque, Integer saldoEndereco){
		this.produto = produto;
		this.deposito = deposito;
		this.saldoEstoque = saldoEstoque;
		this.saldoEndereco = saldoEndereco;
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
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getSaldoEstoque() {
		return saldoEstoque;
	}
	public void setSaldoEstoque(Integer saldoEstoque) {
		this.saldoEstoque = saldoEstoque;
	}
	
	public Integer getSaldoEndereco() {
		return saldoEndereco;
	}
	public void setSaldoEndereco(Integer saldoEndereco) {
		this.saldoEndereco = saldoEndereco;
	}

}
