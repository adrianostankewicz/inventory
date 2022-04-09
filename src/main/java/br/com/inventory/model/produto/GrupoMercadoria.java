package br.com.inventory.model.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_mercadoria")
public class GrupoMercadoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String codigoSAP;
	private String descricao;

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
	
	@Column(name = "codigo_SAP", nullable = false)
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public String getCodigoSAP() {
		return codigoSAP;
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
		GrupoMercadoria other = (GrupoMercadoria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
