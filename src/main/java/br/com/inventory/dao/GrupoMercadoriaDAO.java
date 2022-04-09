package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.produto.GrupoMercadoria;
import br.com.inventory.util.DAOException;

public interface GrupoMercadoriaDAO {

	public void salvar(GrupoMercadoria grupoMercadoria) throws DAOException;
	
	public GrupoMercadoria buscarPorCodigo(Long codigo);
	
	public GrupoMercadoria buscarPorCodigoSAP(String codigoSAP);
	
	public GrupoMercadoria buscarPorDescricao(String descricao);
	
	public List<GrupoMercadoria> listar();
}
