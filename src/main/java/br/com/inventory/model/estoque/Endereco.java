package br.com.inventory.model.estoque;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String descricao;
	private String rua;
	private String modulo;
	private String nivel;
	private String vao;
	private Boolean picking;
	private String unidadeAcondicionamento;

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
	
	@Column(nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(nullable = false)
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}

	@Column(nullable = false)
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	@Column(nullable = false)
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	@Column(nullable = false)
	public String getVao() {
		return vao;
	}
	public void setVao(String vao) {
		this.vao = vao;
	}

	@Column(nullable = false)
	public Boolean getPicking() {
		return picking;
	}
	public void setPicking(Boolean picking) {
		this.picking = picking;
	}

	@Column(name = "unidade_acondicionamento", nullable = false)
	public String getUnidadeAcondicionamento() {
		return unidadeAcondicionamento;
	}
	public void setUnidadeAcondicionamento(String unidadeAcondicionamento) {
		this.unidadeAcondicionamento = unidadeAcondicionamento;
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
		Endereco other = (Endereco) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
