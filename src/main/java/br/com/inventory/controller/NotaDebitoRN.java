package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.inventory.dao.NotaDebitoDAO;
import br.com.inventory.model.auditoria.NotaDebito;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class NotaDebitoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotaDebitoDAO notaDebitoDAO;

	public void salvar(NotaDebito notaDebito) throws RNException{
		try{
			notaDebitoDAO.salvar(notaDebito);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public NotaDebito buscarPorCodigo(Long codigo){
		return notaDebitoDAO.buscarPorCodigo(codigo);
	}
	
	public NotaDebito buscarPorNotaDebito(String notaDebito){
		return notaDebitoDAO.buscarPorNotaDebito(notaDebito);
	}
	
	public List<NotaDebito> listarPorData(Date inicio, Date fim){
		return notaDebitoDAO.listarPorData(inicio, fim);
	}
	
	public List<NotaDebito> listarPorOcorrencia(Ocorrencia ocorrencia){
		return notaDebitoDAO.listarPorOcorrencia(ocorrencia);
	}
}
