package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.GrupoMercadoriaDAO;
import br.com.inventory.model.produto.GrupoMercadoria;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto GrupoMercadoria.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class GruposMercadorias implements Serializable, GrupoMercadoriaDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param GrupoMercadoria
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(GrupoMercadoria grupoMercadoria) throws DAOException {
		try{	
			manager.merge(grupoMercadoria);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o grupo de mercadoria: " + grupoMercadoria.getDescricao() + ". Erro: " + pe.getMessage());
		}	
	}

	/**
	 * Método responsável por recuperar o Grupo de mercadoria por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return GrupoMercadoria
	 * @exception NoResultException
	 */
	@Override
	public GrupoMercadoria buscarPorCodigo(Long codigo) {
		try{
			return manager.find(GrupoMercadoria.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}	
	}
	
	/**
	 * Método responsável por recuperar o Grupo de mercadoria por código SAP.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return GrupoMercadoria
	 * @exception NoResultException
	 */
	@Override
	public GrupoMercadoria buscarPorCodigoSAP(String codigoSAP){
		try{
			return manager.createQuery("from GrupoMercadoria gm where gm.codigoSAP = :codigoSAP", GrupoMercadoria.class)
						  .setParameter("codigoSAP", codigoSAP)
					  	  .getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Grupo de mercadoria por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return GrupoMercadoria
	 * @exception NoResultException
	 */
	@Override
	public GrupoMercadoria buscarPorDescricao(String descricao) {
		try{
			return manager.createQuery("from GrupoMercadoria gm where gm.descricao = :descricao", GrupoMercadoria.class)
					      .setParameter("descricao", descricao)
						  .getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Grupos de mercadoria.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<GrupoMercadoria>
	 */
	@Override
	public List<GrupoMercadoria> listar() {
		return manager.createQuery("from GrupoMercadoria", GrupoMercadoria.class)
					  .getResultList();
	}
}
