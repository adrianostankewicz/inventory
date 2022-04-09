package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.FabricanteDAO;
import br.com.inventory.model.Fabricante;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Fabricante.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Fabricantes implements Serializable, FabricanteDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Fabricante
	 * @throws DAOException
	 * @exception PersistenceException
	 */	
	@Override @Transactional
	public void salvar(Fabricante fabricante) throws DAOException{
		try{
			manager.merge(fabricante);
			
		}catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o fabricante: " + fabricante.getNome() + ". Erro: " + pe.getMessage());
		}
	}
	
	/**
	 * Método responsável por recuperar o Fabricante por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Fabricante
	 * @exception NoResultException
	 */
	@Override
	public Fabricante buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Fabricante.class, codigo);
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Fabricante por nome.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return Fabricante
	 * @exception NoResultException
	 */
	@Override
	public Fabricante buscarPorNome(String nome){
		try{
			return manager.createQuery("from Fabricante f where f.nome = :nome", Fabricante.class)
                	  	  .setParameter("nome", nome)
						  .getSingleResult();
		} catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Fabricantes.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Fabricante>
	 */
	@Override
	public List<Fabricante> listar() {
		return manager.createQuery("from Fabricante", Fabricante.class)
				      .getResultList();
	}
}
