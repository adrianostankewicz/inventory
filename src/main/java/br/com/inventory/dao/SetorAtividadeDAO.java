package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.produto.SetorAtividade;
import br.com.inventory.util.DAOException;

public interface SetorAtividadeDAO {

	public void salvar(SetorAtividade setorAtividade) throws DAOException;
	
	public SetorAtividade buscarPorCodigo(Long codigo);
	
	public SetorAtividade buscarPorCodigoSAP(String codigoSAP);
	
	public SetorAtividade buscarPorDescricao(String descricao);
	
	public List<SetorAtividade> listarTodos();
}
