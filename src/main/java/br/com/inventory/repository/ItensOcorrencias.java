package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.ItemOcorrenciaDAO;
import br.com.inventory.model.auditoria.ItemOcorrencia;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto ItemOcorrencia.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class ItensOcorrencias implements ItemOcorrenciaDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param ItemOcorrencia
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(ItemOcorrencia itemOcorrencia) throws DAOException {
		try{
			manager.merge(itemOcorrencia);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o item " + itemOcorrencia.getProduto().getCodigoSAP() + 
								   ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Item da ocorrência por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return ItemOcorrencia
	 * @exception NoResultException
	 */
	@Override
	public ItemOcorrencia buscarPorCodigo(Long codigo) {
		try{
			return manager.find(ItemOcorrencia.class, codigo);
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar os Itens da ocorrência.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Ocorrencia
	 * @return List<ItemOcorrencia>
	 */
	@Override
	public List<ItemOcorrencia> listarPorOcorrencia(Ocorrencia ocorrencia) {
		return manager.createQuery("from ItemOcorrencia it where it.ocorrencia = :ocorrencia", ItemOcorrencia.class)
					  .setParameter("ocorrencia", ocorrencia)
					  .getResultList();
	}
}
