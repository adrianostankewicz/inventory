package br.com.inventory.model.batimento;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.inventory.model.estoque.Estoque;

@Entity
@Table(name = "batimento_saldo")
public class BatimentoSaldo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Date dataRealizado;
	private Date horaRealizado;
	private StatusBatimento status;
	private Estoque estoque;
	
	@Id
	@GeneratedValue
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_realizado")
	public Date getDataRealizado() {
		return dataRealizado;
	}
	public void setDataRealizado(Date dataRealizado) {
		this.dataRealizado = dataRealizado;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_realizado")
	public Date getHoraRealizado() {
		return horaRealizado;
	}
	public void setHoraRealizado(Date horaRealizado) {
		this.horaRealizado = horaRealizado;
	}
	
	@Enumerated(EnumType.STRING)
	public StatusBatimento getStatus() {
		return status;
	}
	public void setStatus(StatusBatimento status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name = "estoque_id")
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
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
		BatimentoSaldo other = (BatimentoSaldo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
