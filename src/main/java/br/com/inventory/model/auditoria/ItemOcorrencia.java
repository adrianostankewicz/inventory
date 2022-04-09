package br.com.inventory.model.auditoria;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.inventory.model.produto.Produto;

@Entity
@Table(name = "item_ocorrencia")
public class ItemOcorrencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private Ocorrencia ocorrencia;
	private Produto produto;
	private Integer quantidade;
	private Double valor;
	private String serialNumber;
	private MotivoItemOcorrencia motivoItemOcorrencia;
	
	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne
	@JoinColumn(name = "ocorrencia_id")
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@Column(name="serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="motivo_ocorrencia")
	public MotivoItemOcorrencia getMotivoItemOcorrencia() {
		return motivoItemOcorrencia;
	}
	public void setMotivoItemOcorrencia(MotivoItemOcorrencia motivoItemOcorrencia) {
		this.motivoItemOcorrencia = motivoItemOcorrencia;
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
		ItemOcorrencia other = (ItemOcorrencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
