package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.SetorAtividadeDAO;
import br.com.inventory.model.produto.SetorAtividade;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto SetorAtividade.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class SetoresAtividade implements Serializable, SetorAtividadeDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param SetorAtividade
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(SetorAtividade setorAtividade) throws DAOException {
		try{
			manager.merge(setorAtividade);
			
		}catch(PersistenceException e){
			throw new DAOException("Não foi possível salvar o setor de atividade: " + setorAtividade.getCodigoSAP() + ". Erro: " + e.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Setor de atividade por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return SetorAtividade
	 * @exception NoResultException
	 */
	@Override
	public SetorAtividade buscarPorCodigo(Long codigo) {
		try{
			return manager.find(SetorAtividade.class, codigo);
			
		} catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar o Setor de Atividade por código SAP.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return SetorAtividade
	 * @exception NoResultException
	 */
	@Override
	public SetorAtividade buscarPorCodigoSAP(String codigoSAP) {
		try{
			return manager.createQuery("from SetorAtividade st where st.codigoSAP = :codigoSAP", SetorAtividade.class)
						  .setParameter("codigoSAP", codigoSAP)
						  .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por recuperar o Setor de atividade por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return SetorAtividade
	 * @exception NoResultException
	 */
	@Override
	public SetorAtividade buscarPorDescricao(String descricao) {
		try{
			return manager.createQuery("from SetorAtividade st where st.descricao = :descricao", SetorAtividade.class)
						  .setParameter("descricao", descricao)
						  .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todos os Setores de Atividade.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<SetorAtividade>
	 */
	@Override
	public List<SetorAtividade> listarTodos() {
		return manager.createQuery("from SetorAtividade", SetorAtividade.class)
						   .getResultList();
	}
}
