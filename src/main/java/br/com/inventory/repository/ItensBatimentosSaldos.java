package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.ItemBatimentoSaldoDAO;
import br.com.inventory.model.batimento.BatimentoSaldo;
import br.com.inventory.model.batimento.ItemBatimentoSaldo;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto ItemBatimentoSaldo.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class ItensBatimentosSaldos implements ItemBatimentoSaldoDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param ItemBatimentoSaldo
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(ItemBatimentoSaldo itemBatimento) throws DAOException {
		try{
			manager.merge(itemBatimento);
		
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o item " + itemBatimento.getProduto().getCodigoSAP() + 
								   ". Erro " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar o ItemBatimentoSaldo por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return ItemBatimentoSaldo
	 * @exception NoResultException
	 */
	@Override
	public ItemBatimentoSaldo buscarPorCodigo(Long codigo) {
		try{
			return manager.find(ItemBatimentoSaldo.class, codigo);
			
		}catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por buscar um item do batimento por produto.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Produto
	 * @param Deposito
	 * @param BatimentoSaldo
	 * @return ItemBatimentoSaldo
	 */
	@Override
	public ItemBatimentoSaldo buscarPorProduto(Produto produto, Deposito deposito, BatimentoSaldo batimentoSaldo) {
		return manager.createQuery("from ItemBatimentoSaldo ibs where ibs.produto = :produto, ibs.deposito = :deposito,"
								 + "ibs.batimentoSaldo = :batimentoSaldo", ItemBatimentoSaldo.class)
					  .getSingleResult();
	}
	

	/**
	 * Método responsável por listar todos os Itens do Batimento de Saldo.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param BatimentoSaldo
	 * @return List<ItemBatimentoSaldo>
	 */
	@Override
	public List<ItemBatimentoSaldo> listarPorBatimento(BatimentoSaldo batimentoSaldo) {
		return manager.createQuery("from ItemBatimentoSaldo ibs where ibs.batimentoSaldo = :batimentoSaldo", ItemBatimentoSaldo.class)
					  .setParameter("batimentoSaldo", batimentoSaldo)
					  .getResultList();
	}
}
