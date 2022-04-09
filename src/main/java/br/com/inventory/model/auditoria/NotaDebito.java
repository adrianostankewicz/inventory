package br.com.inventory.model.auditoria;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "nota_debito")
public class NotaDebito implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String notaDebito;
	private Responsavel responsavel;
	private Double valor;
	private Date dataNotaDebito;
	private Ocorrencia ocorrencia;
	private String observacoes;

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

	@Column(name = "nota_debito")
	public String getNotaDebito() {
		return notaDebito;
	}
	public void setNotaDebito(String notaDebito) {
		this.notaDebito = notaDebito;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	@Column(nullable = false)
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nota_debito", nullable = false, updatable = false)
	public Date getDataNotaDebito() {
		return dataNotaDebito;
	}
	public void setDataNotaDebito(Date dataNotaDebito) {
		this.dataNotaDebito = dataNotaDebito;
	}
	
	@ManyToOne
	@JoinColumn(name = "ocorrencia_id")
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		NotaDebito other = (NotaDebito) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
