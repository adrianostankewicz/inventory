package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.ItemEstoqueDAO;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.estoque.ItemEstoque;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * Classe responsável pelas transações com o banco de dados do objeto ItemEstoque.
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 */

public class ItensEstoque implements ItemEstoqueDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param ItemEstoque
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(ItemEstoque itemEstoque) throws DAOException {
		try{
			manager.merge(itemEstoque);
		
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar o item " + itemEstoque.getProduto().getCodigoSAP() + 
								   ". Erro: " + pe.getMessage());	
		}
	}

	/**
	 * Método responsável por recuperar o Item do estoque por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return ItemEstoque
	 * @exception NoResultException
	 */
	@Override
	public ItemEstoque buscarPorCodigo(Long codigo) {
		try{
			return manager.find(ItemEstoque.class, codigo);
			
		}catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por recuperar o Item do estoque consolidando o saldo por produto e depósito.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Produto, Deposito
	 * @return ItemEstoque
	 * @exception NoResultException
	 */
	@Override
	public ItemEstoque buscarPorProduto(Produto produto, Deposito deposito) {
		try{
			return manager.createQuery("select ie.codigo, ie.estoque, ie.produto, ie.deposito, ie.avalicaoProduto, sum(ie.saldo), ie.valorUnitario  "
									 + "from ItemEstoque ie where ie.produto = :produto and ie.deposito = :deposito", ItemEstoque.class)
						  .setParameter("produto", produto)
						  .setParameter("deposito", deposito)
						  .getSingleResult();
			
		} catch(NoResultException nre){
			return null;
		}
	}
	
	/**
	 * Método responsável por listar os Itens do estoque consolidado por produto, depósito e saldo.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Estoque
	 * @return List<ItemEstoque>
	 */
	@Override
	public List<ItemEstoque> consolidarSaldoPorProduto(Estoque estoque) {
		return manager.createQuery("select new ItemEstoque(ie.produto, ie.deposito, sum(ie.saldo))  "
								 + "from ItemEstoque ie where ie.estoque = :estoque group by ie.produto, ie.deposito", ItemEstoque.class)
					  .setParameter("estoque", estoque)
					  .getResultList();
	}

	/**
	 * Método responsável por listar todos os Itens do estoque por Estoque.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Estoque
	 * @return List<ItemEstoque>
	 */
	@Override
	public List<ItemEstoque> listarPorEstoque(Estoque estoque) {
		return manager.createQuery("from ItemEstoque ie where ie.estoque = :estoque", ItemEstoque.class)
					  .setParameter("estoque", estoque)
					  .getResultList();
	}
	
	/**
	 * Método responsável por listar todos os Itens de todos os estoques.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<ItemEstoque>
	 */
	@Override
	public List<ItemEstoque> listar(){
		return manager.createQuery("from ItemEstoque ie order by ie.estoque", ItemEstoque.class)
					  .getResultList();
	}

	@Override @Transactional
	public void limpar() {
		for(ItemEstoque ie : listar()){
			manager.remove(ie);
		}
	}
}
