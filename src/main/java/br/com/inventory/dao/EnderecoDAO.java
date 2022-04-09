package br.com.inventory.dao;

import java.util.List;

import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.util.DAOException;

public interface EnderecoDAO {

	public void salvar(Endereco endereco) throws DAOException;
	
	public Endereco buscarPorCodigo(Long codigo);
	
	public Endereco buscarPorDescricao(String descricao);
	
	public List<Endereco> listar();
}
