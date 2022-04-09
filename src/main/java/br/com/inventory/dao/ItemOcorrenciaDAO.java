package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.auditoria.ItemOcorrencia;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;

public interface ItemOcorrenciaDAO {

	public void salvar(ItemOcorrencia itemOcorrencia) throws DAOException;
	
	public ItemOcorrencia buscarPorCodigo(Long codigo);
	
	public List<ItemOcorrencia> listarPorOcorrencia(Ocorrencia ocorrencia);
	
}
