package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.estoque.Enderecamento;
import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;

public interface EnderecamentoDAO {

	public void salvar(Enderecamento enderecamento) throws DAOException;
	
	public Enderecamento buscarPorCodigo(Long codigo);
	
	public List<Enderecamento> buscarPorEndereco(Endereco endereco);
	
	public List<Enderecamento> consolidarSaldoPorProduto(Estoque estoque);
	
	public List<Enderecamento> listarPorEstoque(Estoque estoque);
	
	public List<Enderecamento> listarPorProduto(Produto produto);
}
