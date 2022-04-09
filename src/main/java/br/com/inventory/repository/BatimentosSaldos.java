package br.com.inventory.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.BatimentoSaldoDAO;
import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto BatimentoSaldo.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class BatimentosSaldos implements Serializable, BatimentoSaldoDAO {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param BatimentoSaldo
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(BatimentoSaldo batimentoSaldo) throws DAOException {
		try{
			manager.merge(batimentoSaldo);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o batimento de saldo. Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o Batimento de saldo por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return BatimentoSaldo
	 * @exception NoResultException
	 */
	@Override
	public BatimentoSaldo buscarPorCodigo(Long codigo) {
		try{	
			return manager.find(BatimentoSaldo.class, codigo);
		} catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por recuperar o Batimento de saldo de uma data e estoque pré selecionado.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Date, Estoque
	 * @return BatimentoSaldo
	 * @exception NoResultException
	 */
	@Override
	public BatimentoSaldo buscarPorDataRealizado(Date dataRealizado, Estoque estoque) {
		try{
			return manager.createQuery("from BatimentoSaldo bs where bs.dataRealizado = :dataRealizado and  bs.estoque = :estoque", BatimentoSaldo.class)
						  .setParameter("dataRealizado", dataRealizado)
						  .setParameter("estoque", estoque)
						  .getSingleResult();
			
		}catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar uma listagem de Batimentos de saldo entre duas datas pré selecionadas e estoque específico.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Date, Date, Estoque
	 * @return List<BatimentoSaldo>
	 */
	@Override
	public List<BatimentoSaldo> listarPorEstoque(Date inicio, Date fim, Estoque estoque) {
		return manager.createQuery("from BatimentoSaldo bs where bs.dataRealizado between :inicio and :fim and bs.estoque = :estoque", BatimentoSaldo.class)
					  .setParameter("inicio", inicio)
					  .setParameter("fim", fim)
					  .setParameter("estoque", estoque)
					  .getResultList();
	}
	
	/**
	 * Método responsável por recuperar uma listagem de Batimentos de saldo entre duas datas pré selecionadas.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Date, Date
	 * @return List<BatimentoSaldo>
	 */
	public List<BatimentoSaldo> listarPorData(Date inicio, Date fim){
		return manager.createQuery("from BatimentoSaldo bs where bs.dataRealizado between :inicio and :fim", BatimentoSaldo.class)
					  .setParameter("inicio", inicio)
					  .setParameter("fim", fim)
					  .getResultList();
	}
}
