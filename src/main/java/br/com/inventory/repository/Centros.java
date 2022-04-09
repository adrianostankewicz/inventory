package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.CentroDAO;
import br.com.inventory.model.Centro;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Centro.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Centros implements CentroDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Centro
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Centro centro) throws DAOException{
		try{
			manager.merge(centro);
			
		}catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o centro: " + centro.getDescricao() + ". Erro: " + pe.getMessage());	
		}
	}

	/**
	 * Método responsável por recuperar o Centro por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Centro
	 * @exception NoResultException
	 */
	@Override
	public Centro buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Centro.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Centro por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return Centro
	 * @exception NoResultException
	 */
	@Override
	public Centro buscarPorDescricao(String descricao) {
		try{
			return manager.createQuery("from Centro c where c.descricao = :descricao", Centro.class)
						  .setParameter("descricao", descricao)
					      .getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Centros.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Centro>
	 */
	@Override
	public List<Centro> listar() {
		return manager.createQuery("from Centro", Centro.class)
				      .getResultList();
	}
}
