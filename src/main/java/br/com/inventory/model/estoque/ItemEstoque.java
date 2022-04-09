package br.com.inventory.model.estoque;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.inventory.model.produto.AvaliacaoProduto;
import br.com.inventory.model.produto.Produto;

@Entity
@Table(name="item_estoque")
public class ItemEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Estoque estoque;
	private Produto produto;
	private Deposito deposito;
	private AvaliacaoProduto avaliacaoProduto;
	private Integer saldo;
	private Double valorUnitario;
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	@Id
	@GeneratedValue
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne
	@JoinColumn(name="estoque_id", nullable = false)
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false)
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@ManyToOne
	@JoinColumn(name = "deposito_id", nullable = false)
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	@ManyToOne
	@JoinColumn(name = "avaliacao_produto_id", nullable = false)
	public AvaliacaoProduto getAvaliacaoProduto() {
		return avaliacaoProduto;
	}
	public void setAvaliacaoProduto(AvaliacaoProduto avaliacaoProduto) {
		this.avaliacaoProduto = avaliacaoProduto;
	}
	
	@Column(name = "saldo")
	public Integer getSaldo() {
		return saldo;
	}
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	@Column(name = "valor_unitario")
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public ItemEstoque(){}
	
	public ItemEstoque(Produto produto, Deposito deposito, Long saldo){
		this.produto = produto;
		this.deposito = deposito;
		this.saldo = saldo.intValue();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemEstoque other = (ItemEstoque) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
