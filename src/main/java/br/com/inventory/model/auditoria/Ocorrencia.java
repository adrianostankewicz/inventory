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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private Date dataOcorrencia;
	private Date dataFinalizado;
	private StatusOcorrencia statusOcorrencia;
	private MotivoOcorrencia motivoOcorrencia;
	private String observacoes;
	private Responsavel responsavel;
	private String notaFiscalOrigem;
	private Date dataNotaFiscalOrigem;	
	private String rnc;
	private Date dataRnc;
	private String chamado;
	private Date dataChamado;
	private Date dataConclusaoChamado;

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

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ocorrencia", nullable = false, updatable = false)
	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_finalizado")
	public Date getDataFinalizado() {
		return dataFinalizado;
	}
	public void setDataFinalizado(Date dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status_ocorrencia", nullable = false)
	public StatusOcorrencia getStatusOcorrencia() {
		return statusOcorrencia;
	}
	public void setStatusOcorrencia(StatusOcorrencia statusOcorrencia) {
		this.statusOcorrencia = statusOcorrencia;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "motivo_ocorrencia", nullable = false)
	public MotivoOcorrencia getMotivoOcorrencia() {
		return motivoOcorrencia;
	}
	public void setMotivoOcorrencia(MotivoOcorrencia motivoOcorrencia) {
		this.motivoOcorrencia = motivoOcorrencia;
	}

	
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	@Column(name="nota_fiscal_origem")
	public String getNotaFiscalOrigem() {
		return notaFiscalOrigem;
	}
	public void setNotaFiscalOrigem(String notaFiscalOrigem) {
		this.notaFiscalOrigem = notaFiscalOrigem;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_nota_fiscal_origem")
	public Date getDataNotaFiscalOrigem() {
		return dataNotaFiscalOrigem;
	}
	public void setDataNotaFiscalOrigem(Date dataNotaFiscalOrigem) {
		this.dataNotaFiscalOrigem = dataNotaFiscalOrigem;
	}

	public String getRnc() {
		return rnc;
	}
	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_rnc")
	public Date getDataRnc() {
		return dataRnc;
	}
	public void setDataRnc(Date dataRnc) {
		this.dataRnc = dataRnc;
	}

	public String getChamado() {
		return chamado;
	}
	public void setChamado(String chamado) {
		this.chamado = chamado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_chamado")
	public Date getDataChamado() {
		return dataChamado;
	}
	public void setDataChamado(Date dataChamado) {
		this.dataChamado = dataChamado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_conclusao_chamado")
	public Date getDataConclusaoChamado() {
		return dataConclusaoChamado;
	}
	public void setDataConclusaoChamado(Date dataConclusaoChamado) {
		this.dataConclusaoChamado = dataConclusaoChamado;
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
		Ocorrencia other = (Ocorrencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
