package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.ProdutoDAO;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Produto.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Produtos implements ProdutoDAO, Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Produto
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Produto produto) throws DAOException {
		try{
			manager.merge(produto);
			
		}catch(PersistenceException e){
			throw new DAOException("Não foi possível salvar o produto " + produto.getCodigoSAP() + ". Erro: " + e.getMessage());
		}
	}
	
	/**
	 * Método responsável por recuperar o Produto por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Produto
	 * @exception NoResultException
	 */
	@Override
	public Produto buscarPorCodigo(Long codigo) {
		try {
			return manager.find(Produto.class, codigo);
			
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Produto por código SAP.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Integer
	 * @return Produto
	 * @exception NoResultException
	 */
	@Override
	public Produto buscarPorCodigoSAP(Integer codigoSAP) {
		try{
			return manager.createQuery("from Produto p where p.codigoSAP = :codigoSAP", Produto.class)
						  .setParameter("codigoSAP", codigoSAP)
						  .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Produtos.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Produto>
	 */
	@Override
	public List<Produto> listar() {
		return manager.createQuery("from Produto", Produto.class)
					  .getResultList();
	}

}
