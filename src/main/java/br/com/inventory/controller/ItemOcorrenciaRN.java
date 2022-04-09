package br.com.inventory.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.inventory.dao.ItemOcorrenciaDAO;
import br.com.inventory.model.auditoria.ItemOcorrencia;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class ItemOcorrenciaRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemOcorrenciaDAO itemOcorrenciaDAO;
	
	public void salvar(ItemOcorrencia itemOcorrencia) throws RNException{
		try {
			itemOcorrenciaDAO.salvar(itemOcorrencia);
			
		} catch (DAOException de) {
			throw new RNException(de.getMessage());
		}
	}
	
	public ItemOcorrencia buscarPorCodigo(Long codigo){
		return itemOcorrenciaDAO.buscarPorCodigo(codigo);
	}
	
	public List<ItemOcorrencia> listarPorOcorrencia(Ocorrencia ocorrencia){
		return itemOcorrenciaDAO.listarPorOcorrencia(ocorrencia);
	}
}
