package br.com.inventory.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.OcorrenciaDAO;
import br.com.inventory.model.auditoria.Ocorrencia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto Ocorrencia.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class Ocorrencias implements OcorrenciaDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param Ocorrencia
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(Ocorrencia ocorrencia) throws DAOException {
		try{
			manager.merge(ocorrencia);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar a ocorrência de QM: " + ocorrencia.getChamado() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar a Ocorrência por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return Ocorrencia
	 * @exception NoResultException
	 */
	@Override
	public Ocorrencia buscarPorCodigo(Long codigo) {
		try{
			return manager.find(Ocorrencia.class, codigo);
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por listar todas as Ocorrências entre duas datas.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Date, Date
	 * @return List<Ocorrencia>
	 */
	@Override
	public List<Ocorrencia> listarPorData(Date inicio, Date fim) {
		return manager.createQuery("from Ocorrencia o where o.dataOcorrencia between :inicio and :fim", Ocorrencia.class)
					  .setParameter("inicio", inicio)
					  .setParameter("fim", fim)
					  .getResultList();
	}

	/**
	 * Método responsável por listar todas as Ocorrências por Nota Fiscal Officer.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return List<Ocorrencia>
	 */
	@Override
	public List<Ocorrencia> listarPorNotaFiscalOrigem(String notaFiscalOrigem) {
	return manager.createQuery("from Ocorrencia o where o.notaFiscalOrigem = :notaFiscalOrigem", Ocorrencia.class)
				  .setParameter("notaFiscalOrigem", notaFiscalOrigem)
				  .getResultList();
	}
}
