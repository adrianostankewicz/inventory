package br.com.inventory.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.NotaDebitoDAO;
import br.com.inventory.model.auditoria.NotaDebito;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto NotaDebito.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */
public class NotasDebitos implements NotaDebitoDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param NotaDebito
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(NotaDebito notaDebito) throws DAOException{
		try{
			manager.merge(notaDebito);
		
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar a nota de débito: " + notaDebito.getNotaDebito() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar a Nota de Débito por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return NotaDebito
	 * @exception NoResultException
	 */
	@Override
	public NotaDebito buscarPorCodigo(Long codigo) {
		try{
			return manager.find(NotaDebito.class, codigo);
			
		}catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por recuperar a Nota de Débito por identificação.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return NotaDebito
	 * @exception NoResultException
	 */
	@Override
	public NotaDebito buscarPorNotaDebito(String notaDebito){
		try{
			return manager.createQuery("from NotaDebito nd where nd.notaDebito = :notaDebito", NotaDebito.class)
						  .setParameter("notaDebito", notaDebito)
						  .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todas as Notas de Débito entre duas datas.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Date, Date
	 * @return List<NotaDebito>
	 */
	@Override
	public List<NotaDebito> listarPorData(Date inicio, Date fim) {
		return manager.createQuery("from NotaDebito nd where nd.dataNotaDebito between :inicio and :fim", NotaDebito.class)
					  .setParameter("inicio", inicio)
					  .setParameter("fim", fim)
					  .getResultList();
	}

	/**
	 * Método responsável por listar todas as Notas de Débito por ocorrência.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Ocorrencia
	 * @return List<NotaDebito>
	 */
	@Override
	public List<NotaDebito> listarPorOcorrencia(Ocorrencia ocorrencia) {
		return manager.createQuery("from NotaDebito nd where nd.ocorrencia = :ocorrencia", NotaDebito.class)
					  .setParameter("ocorrencia", ocorrencia)
					  .getResultList();
	}
}
