package br.com.inventory.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.inventory.dao.AvaliacaoProdutoDAO;
import br.com.inventory.model.produto.AvaliacaoProduto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.jpa.Transactional;

/**
 * @author Adriano Stankewicz
 * @since 1.0
 * @version 2.0
 * Classe responsável pelas transações com o banco de dados do objeto AvaliacaoProduto.
 */

public class AvaliacoesProdutos implements AvaliacaoProdutoDAO, Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/**
	 * Método responsável por salvar o objeto no banco de dados.
	 * @author Adriano Stankewicz
	 * @since 2.0
	 * @param AvaliacaoProduto
	 * @throws DAOException
	 * @exception PersistenceException
	 */
	@Override @Transactional
	public void salvar(AvaliacaoProduto avaliacaoProduto) throws DAOException {
		try{
			manager.merge(avaliacaoProduto);
			
		} catch(PersistenceException pe){
			throw new DAOException("Não foi possível salvar a avaliação de produto: " + avaliacaoProduto.getDescricao() + ". Erro: " + pe.getMessage());
		}
	}

	/**
	 * Método responsável por recuperar a Avaliação de produto por código.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param Long
	 * @return AvaliacaoProduto
	 * @exception NoResultException
	 */
	@Override
	public AvaliacaoProduto buscarPorCodigo(Long codigo) {
		try{
			return manager.find(AvaliacaoProduto.class, codigo);
		} catch(NoResultException nre){
			return null;
		}
	}

	/**
	 * Método responsável por recuperar a Avaliação de produto por descrição.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @param String
	 * @return AvaliacaoProduto
	 * @exception NoResultException
	 */
	@Override
	public AvaliacaoProduto buscarPorDescricao(String descricao) {
		try {
			return manager.createQuery("from AvaliacaoProduto ap where ap.descricao = :descricao", AvaliacaoProduto.class)
						  .setParameter("descricao", descricao)
						  .getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Método responsável por listar todas as Avaliações de produto.
	 * @author Adriano Stankewicz
	 * @since 1.0
	 * @return List<AvaliacaoProduto>
	 */
	@Override
	public List<AvaliacaoProduto> listar() {
		return manager.createQuery("from AvaliacaoProduto", AvaliacaoProduto.class)
					  .getResultList();
	}
}
