package br.com.inventory.model.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.inventory.model.Fabricante;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private Integer codigoSAP;
	private String descricao;
	private String partNumber;
	private Long ean;
	private Double peso;
	private TipoProduto tipoProduto;
	private GrupoMercadoria grupoMercadoria;
	private Hierarquia hierarquia;
	private Fabricante fabricante;
	private Double largura;
	private Double altura;
	private Double comprimento;
	private Double volume;
	private Boolean serialNumber;
	private Long caixaMaster;
	private Long paletizacao;
	private SetorAtividade setorAtividade;

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
	public Integer getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(Integer codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	public Long getEan() {
		return ean;
	}
	public void setEan(Long ean) {
		this.ean = ean;
	}
	
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	@ManyToOne
	@JoinColumn(name = "tipo_produto_id")
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	@ManyToOne
	@JoinColumn(name = "grupo_mercadoria_id", nullable = false)
	public GrupoMercadoria getGrupoMercadoria() {
		return grupoMercadoria;
	}
	public void setGrupoMercadoria(GrupoMercadoria grupoMercadoria) {
		this.grupoMercadoria = grupoMercadoria;
	}

	@ManyToOne
	@JoinColumn(name = "hierarquia_id", nullable = false)
	public Hierarquia getHierarquia() {
		return hierarquia;
	}
	public void setHierarquia(Hierarquia hierarquia) {
		this.hierarquia = hierarquia;
	}

	@ManyToOne
	@JoinColumn(name = "fabricante_id", nullable = false)
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public Double getLargura() {
		return largura;
	}
	public void setLargura(Double largura) {
		this.largura = largura;
	}
	
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	
	public Double getComprimento() {
		return comprimento;
	}
	public void setComprimento(Double comprimento) {
		this.comprimento = comprimento;
	}
	
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
	public Boolean getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Boolean serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Long getCaixaMaster() {
		return caixaMaster;
	}
	public void setCaixaMaster(Long caixaMaster) {
		this.caixaMaster = caixaMaster;
	}
	
	public Long getPaletizacao() {
		return paletizacao;
	}
	public void setPaletizacao(Long paletizacao) {
		this.paletizacao = paletizacao;
	}
	
	@ManyToOne
	@JoinColumn(name = "setor_atividade_id", nullable = false)
	public SetorAtividade getSetorAtividade() {
		return setorAtividade;
	}
	public void setSetorAtividade(SetorAtividade setorAtividade) {
		this.setorAtividade = setorAtividade;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
