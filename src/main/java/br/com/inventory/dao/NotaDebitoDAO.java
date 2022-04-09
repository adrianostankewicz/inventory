package br.com.inventory.dao;

import java.util.Date;
import java.util.List;

import br.com.inventory.model.auditoria.NotaDebito;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;

public interface NotaDebitoDAO {

	public void salvar(NotaDebito notaDebito) throws DAOException;
	
	public NotaDebito buscarPorCodigo(Long codigo);
	
	public NotaDebito buscarPorNotaDebito(String notaDebito);
	
	public List<NotaDebito> listarPorData(Date inicio, Date fim);
	
	public List<NotaDebito> listarPorOcorrencia(Ocorrencia ocorrencia);
	
}
