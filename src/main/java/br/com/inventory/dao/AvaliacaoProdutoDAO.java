package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.produto.AvaliacaoProduto;
import br.com.inventory.util.DAOException;

public interface AvaliacaoProdutoDAO {

	public void salvar(AvaliacaoProduto avaliacaoProduto) throws DAOException;
	
	public AvaliacaoProduto buscarPorCodigo(Long codigo);
	
	public AvaliacaoProduto buscarPorDescricao(String descricao);
	
	public List<AvaliacaoProduto> listar();
	
}
