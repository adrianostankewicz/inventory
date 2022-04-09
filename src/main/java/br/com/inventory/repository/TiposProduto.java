package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.TipoProdutoDAO;
import br.com.inventory.model.produto.TipoProduto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto TipoProduto.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class TiposProduto implements Serializable, TipoProdutoDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param TipoProduto
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(TipoProduto tipoProduto) throws DAOException {
		try{
			manager.merge(tipoProduto);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o tipo de produto: " + tipoProduto.getCodigoSAP() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Tipo de produto por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return TipoProduto
	 * @exception NoResultException
	 */
	@Override
	public TipoProduto buscarPorCodigo(Long codigo) {
		try{
			return manager.find(TipoProduto.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por recuperar o Tipo de produto por código SAP.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return TipoProduto
	 * @exception NoResultException
	 */
	@Override
	public TipoProduto buscarPorCodigoSAP(String codigoSAP) {
		try{
			return manager.createQuery("from TipoProduto tp where tp.codigoSAP = :codigoSAP", TipoProduto.class)
							   .setParameter("codigoSAP", codigoSAP)
							   .getSingleResult();
			
		} catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Tipos de produto.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<TipoProduto>
	 */
	@Override
	public List<TipoProduto> listar() {
		return this.manager.createQuery("from TipoProduto", TipoProduto.class)
						   .getResultList();
	}

}
