package br.com.inventory.dao;

import java.util.Date;
import java.util.List;

import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;

public interface OcorrenciaDAO {

	public void salvar(Ocorrencia ocorrencia) throws DAOException;
	
	public Ocorrencia buscarPorCodigo(Long codigo);
	
	public List<Ocorrencia> listarPorData(Date inicio, Date fim);
	
	public List<Ocorrencia> listarPorNotaFiscalOrigem(String notaFiscalOrigem);
}
