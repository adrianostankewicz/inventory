package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.EnderecoDAO;
import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Endereco.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Enderecos implements EnderecoDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Endereco
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Endereco endereco) throws DAOException {
		try{
			manager.merge(endereco);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o endereço: " + endereco.getDescricao() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Endereço por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Endereco
	 * @exception NoResultException
	 */
	@Override
	public Endereco buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Endereco.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}	
	}

	/**
	 * Método responsável por recuperar o Endereço por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return Endereco
	 * @exception NoResultException
	 */
	@Override
	public Endereco buscarPorDescricao(String descricao) {
		try{
			return manager.createQuery("from Endereco e where e.descricao = :descricao", Endereco.class)
						  .setParameter("descricao", descricao)
					      .getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Endereços.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<Endereco>
	 */
	@Override
	public List<Endereco> listar() {
		return manager.createQuery("from Endereco", Endereco.class)
					  .getResultList();
	}

}
