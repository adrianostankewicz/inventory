package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.DepositoDAO;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Depósito.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Depositos implements DepositoDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Deposito
	 * @throws DAOException
	 * @exception PersistenceException
	 */	
	@Override @Transactional
	public void salvar(Deposito deposito) throws DAOException {
		try{
			manager.merge(deposito);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o depósito: " + deposito.getDescricao() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Depósito por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Deposito
	 * @exception NoResultException
	 */
	@Override
	public Deposito buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Deposito.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Depósito por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return Deposito
	 * @exception NoResultException
	 */
	@Override
	public Deposito buscarPorDescricao(String descricao) {
		try{
			return manager.createQuery("from Deposito d where d.descricao = :descricao", Deposito.class)
						  .setParameter("descricao", descricao)
					      .getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por listar todos os Depósitos.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Deposito>
	 */
	@Override
	public List<Deposito> listar() {
		return manager.createQuery("from Deposito", Deposito.class)
					  .getResultList();
	}
}
