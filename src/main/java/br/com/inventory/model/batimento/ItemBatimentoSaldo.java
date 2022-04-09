package br.com.inventory.model.batimento;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.produto.Produto;

@Entity
@Table(name = "item_batimento_saldo")
public class ItemBatimentoSaldo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private Deposito deposito;
	private Produto produto;
	private Integer saldoEstoque;
	private Integer saldoEndereco;
	private Integer divergencia;
	private Integer ajuste;
	private Integer divergenciaFinal;
	private String justificativa;
	private MotivoDivergenciaBatimento motivo;
	private ResponsavelDivergenciaBatimento responsavel;
	private StatusItemBatimento statusItem;
	private BatimentoSaldo batimentoSaldo;
	
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
	@JoinColumn(name = "deposito_id")
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Column(name = "saldo_estoque")
	public Integer getSaldoEstoque() {
		return saldoEstoque;
	}
	public void setSaldoEstoque(Integer saldoEstoque) {
		this.saldoEstoque = saldoEstoque;
	}
	
	@Column(name = "saldo_endereco")
	public Integer getSaldoEndereco() {
		return saldoEndereco;
	}
	public void setSaldoEndereco(Integer saldoEndereco) {
		this.saldoEndereco = saldoEndereco;
	}
	
	public Integer getDivergencia() {
		return divergencia;
	}
	public void setDivergencia(Integer divergencia) {
		this.divergencia = divergencia;
	}

	public Integer getAjuste() {
		return ajuste;
	}
	public void setAjuste(Integer ajuste) {
		this.ajuste = ajuste;
	}

	@Column(name = "divergencia_final")
	public Integer getDivergenciaFinal() {
		return divergenciaFinal;
	}
	public void setDivergenciaFinal(Integer divergenciaFinal) {
		this.divergenciaFinal = divergenciaFinal;
	}

	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "motivo_divergencia_batimento")
	public MotivoDivergenciaBatimento getMotivo() {
		return motivo;
	}
	public void setMotivo(MotivoDivergenciaBatimento motivo) {
		this.motivo = motivo;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "responsavel_divergencia_batimento")
	public ResponsavelDivergenciaBatimento getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(ResponsavelDivergenciaBatimento responsavel) {
		this.responsavel = responsavel;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status_item_batimento")
	public StatusItemBatimento getStatusItem() {
		return statusItem;
	}
	public void setStatusItem(StatusItemBatimento statusItem) {
		this.statusItem = statusItem;
	}
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="batimento_saldo_id")
	public BatimentoSaldo getBatimentoSaldo() {
		return batimentoSaldo;
	}
	public void setBatimentoSaldo(BatimentoSaldo batimentoSaldo) {
		this.batimentoSaldo = batimentoSaldo;
	}
	
	/**********************
	 * REGRAS DE NEGÓCIO  *
	 **********************/
	
	public void ajustarDivergencia(){
		calcularDivergenciaFinal();
		
		if(divergenciaFinal == 0){
			statusItem = StatusItemBatimento.OK;
		}
	}
	
	public void calcularDivergencia(){
		divergencia = Math.abs(saldoEstoque) - Math.abs(saldoEndereco);
	}
	
	public void calcularDivergenciaFinal(){
		divergenciaFinal = Math.abs(divergencia) - Math.abs(ajuste);
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
		ItemBatimentoSaldo other = (ItemBatimentoSaldo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
