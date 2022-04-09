package br.com.inventory.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.inventory.controller.ItemOcorrenciaRN;
import br.com.inventory.controller.OcorrenciaRN;
import br.com.inventory.controller.ProdutoRN;
import br.com.inventory.model.auditoria.ItemOcorrencia;
import br.com.inventory.model.auditoria.MotivoItemOcorrencia;
import br.com.inventory.model.auditoria.MotivoOcorrencia;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.model.auditoria.Responsavel;
import br.com.inventory.model.auditoria.StatusOcorrencia;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

@ViewScoped
@Named("ocorrenciaBean")
public class OcorrenciaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Ocorrencia ocorrencia;
	private ItemOcorrencia itemOcorrencia;
	private Produto produto;
	private List<ItemOcorrencia> itens;

	@Inject
	private OcorrenciaRN ocorrenciaRN;
	
	@Inject
	private ProdutoRN produtoRN;
	
	@Inject
	private ItemOcorrenciaRN itemOcorrenciaRN;
	
	public OcorrenciaBean(){
		ocorrencia = new Ocorrencia();
		itemOcorrencia = new ItemOcorrencia();
	}
	
	public void salvar() {
		try {
			ocorrenciaRN.salvar(ocorrencia);

			for (ItemOcorrencia item : itens) {

				item.setOcorrencia(ocorrencia);

				produto = new Produto();
				produto = produtoRN.buscarPorCodigoSAP(item.getProduto().getCodigoSAP());
				item.setProduto(produto);

				itemOcorrenciaRN.salvar(item);
			}

			ocorrencia = new Ocorrencia();
			itens.clear();
			FacesUtil.addSuccessMessage("Ocorrência salvo com sucesso.");

		} catch (RNException rne) {
			FacesUtil.addErrorMessage(rne.getMessage());
		}
	}

	public void adicionarItem() {

		Boolean encontrou = false;

		for (ItemOcorrencia i : itens) {
			if (itemOcorrencia.getProduto().getCodigoSAP() == i.getProduto().getCodigoSAP()
					&& itemOcorrencia.getSerialNumber().equalsIgnoreCase(i.getSerialNumber())) {
				encontrou = true;
				break;
			}
		}

		if (!encontrou) {
			itens.add(itemOcorrencia);
		} else {
			FacesUtil.addErrorMessage("Item já adicionado.");
		}
		itemOcorrencia = new ItemOcorrencia();
	}

	public void adicionarDescricaoProduto() {

		produto = new Produto();
		produto = produtoRN.buscarPorCodigoSAP(itemOcorrencia.getProduto().getCodigoSAP());

		if (produto.equals(null)) {
			FacesUtil.addErrorMessage("Produto não encontrado.");
			
		} else {
			itemOcorrencia.getProduto().setDescricao(produto.getDescricao());
		}
	}

	/**********************
	 * GETTERS AND SETTERS*
	 **********************/
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ItemOcorrencia getItemOcorrencia() {
		return itemOcorrencia;
	}
	public void setItemOcorrencia(ItemOcorrencia itemOcorrencia) {
		this.itemOcorrencia = itemOcorrencia;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	
	public List<ItemOcorrencia> getItens() {
		return itens;
	}
	public void setItens(List<ItemOcorrencia> itens) {
		this.itens = itens;
	}

	public MotivoOcorrencia[] getMotivoOcorrencias() {
		return MotivoOcorrencia.values();
	}

	public StatusOcorrencia[] getStatusOcorrencias() {
		return StatusOcorrencia.values();
	}

	public Responsavel[] getResponsaveis() {
		return Responsavel.values();
	}

	public MotivoItemOcorrencia[] getMotivoItemOcorrencias() {
		return MotivoItemOcorrencia.values();
	}
}
