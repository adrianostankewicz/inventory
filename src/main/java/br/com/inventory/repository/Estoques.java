package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.EstoqueDAO;
import br.com.inventory.model.Centro;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Estoque.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Estoques implements Serializable, EstoqueDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Estoque
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Estoque estoque) throws DAOException {
		try{
			manager.merge(estoque);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o estoque: " + estoque.getCentro().getDescricao() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Estoque por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Estoque
	 * @exception NoResultException
	 */
	@Override
	public Estoque buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Estoque.class, codigo);
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Estoque por centro.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Centro
	 * @return Estoque
	 * @exception NoResultException
	 */
	@Override
	public Estoque buscarPorCentro(Centro centro) {
		try{
			return manager.createQuery("from Estoque e where e.centro = :centro", Estoque.class)
				      	  .setParameter("centro", centro)	
						  .getSingleResult();
					
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Estoques.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Estoque>
	 */
	@Override
	public List<Estoque> listar() {
		return manager.createQuery("from Estoque", Estoque.class)
					  .getResultList();
	}

}
