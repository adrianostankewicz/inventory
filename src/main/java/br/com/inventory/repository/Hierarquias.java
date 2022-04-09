package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.HierarquiaDAO;
import br.com.inventory.model.produto.Hierarquia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Hierarquia.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Hierarquias implements Serializable, HierarquiaDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Hierarquia
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Hierarquia hierarquia) throws DAOException {
		try{
			manager.merge(hierarquia);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi posssível salvar a hierarquia: " + hierarquia.getDescricao() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Hierarquia por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Hierarquia
	 * @exception NoResultException
	 */
	@Override
	public Hierarquia buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Hierarquia.class, codigo);
		
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar a Hierarquia por código SAP.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return Hierarquia
	 * @exception NoResultException
	 */
	@Override
	public Hierarquia buscarPorCodigoSAP(String codigoSAP) {
		try{
			return manager.createQuery("from Hierarquia h where h.codigoSAP = :codigoSAP", Hierarquia.class)
						  .setParameter("codigoSAP", codigoSAP)
					      .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar a Hierarquia por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return Hierarquia
	 * @exception NoResultException
	 */
	@Override
	public Hierarquia buscarPorDescricao(String descricao) {
		try{
			return manager.createQuery("from Hierarquia h where h.descricao = :descricao", Hierarquia.class)
						  .setParameter("descricao", descricao)
						  .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todas as Hierarquias.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Hierarquia>
	 */
	@Override
	public List<Hierarquia> listar() {
		return manager.createQuery("from Hierarquia", Hierarquia.class)
					  .getResultList();
	}

}
