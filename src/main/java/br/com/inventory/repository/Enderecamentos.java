package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.EnderecamentoDAO;
import br.com.inventory.model.estoque.Enderecamento;
import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Enderecamento.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Enderecamentos implements Serializable, EnderecamentoDAO {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Enderecamento
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Enderecamento enderecamento) throws DAOException {
		try{
			manager.merge(enderecamento);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o endereçamento: " + enderecamento.getEndereco().getDescricao() + ". Erro: " + pe.getMessage());	
		}
	}

	/**
	 * Método responsável por recuperar o Endereçamento por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Enderecamento
	 * @exception NoResultException
	 */
	@Override
	public Enderecamento buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Enderecamento.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}	
	}

	/**
	 * Método responsável por listar os Endereçamentos por endereço.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Endereco
	 * @return List<Enderecamento>
	 */
	@Override
	public List<Enderecamento> buscarPorEndereco(Endereco endereco) {
		return manager.createQuery("from Enderecamento ende where ende.endereco = :endereco", Enderecamento.class)
					  .setParameter("endereco", endereco)
					  .getResultList();
	}
	
	/**
	 * Método responsável por listar os Endereçamentos consolidados por saldo do produto e por estoque.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Estoque
	 * @return List<Enderecamento>
	 */
	@Override
	public List<Enderecamento> consolidarSaldoPorProduto(Estoque estoque){
		return manager.createQuery("select new Enderecamento(ende.produto, ende.deposito, sum(ende.saldo))"
								 + " from Enderecamento ende where ende.estoque = :estoque "
								 + " group by ende.produto, ende.deposito", Enderecamento.class)
				  	  .setParameter("estoque", estoque)
				  	  .getResultList();
	}
	
	/**
	 * Método responsável por listar os Endereçamentos por estoque.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Estoque
	 * @return List<Enderecamento>
	 */
	@Override
	public List<Enderecamento> listarPorEstoque(Estoque estoque) {
		return manager.createQuery("from Enderecamento ende where ende.estoque = :estoque", Enderecamento.class)
					  .setParameter("estoque", estoque)
					  .getResultList();
	}
	
	/**
	 * Método responsável por listar os Endereçamentos por produto.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Produto
	 * @return List<Enderecamento>
	 */
	@Override
	public List<Enderecamento> listarPorProduto(Produto produto) {
		return manager.createQuery("from Enderecamento ende where ende.produto = :produto", Enderecamento.class)
					  .setParameter("produto", produto)
					  .getResultList();	
	}
}
