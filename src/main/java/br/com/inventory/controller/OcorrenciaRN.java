package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.inventory.dao.OcorrenciaDAO;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class OcorrenciaRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OcorrenciaDAO ocorrenciaDAO;
	
	public void salvar(Ocorrencia ocorrencia) throws RNException{
		try{
			ocorrenciaDAO.salvar(ocorrencia);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public Ocorrencia buscarPorCodigo(Long codigo){
		return ocorrenciaDAO.buscarPorCodigo(codigo);
	}
	
	public List<Ocorrencia> listarPorData(Date inicio, Date fim){
		return ocorrenciaDAO.listarPorData(inicio, fim);
	}
	
	public List<Ocorrencia> listarPorNotaFiscalOrigem(String notaFiscalOrigem){
		return ocorrenciaDAO.listarPorNotaFiscalOrigem(notaFiscalOrigem);
	}

}
