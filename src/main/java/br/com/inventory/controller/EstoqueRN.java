package br.com.inventory.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.EstoqueDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.log.EstoqueLog;
import br.com.inventory.model.Centro;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.estoque.ItemEstoque;
import br.com.inventory.model.estoque.ItemEstoqueNaoCadastrado;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class EstoqueRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstoqueDAO estoqueDAO;
	
	@Inject
	private ProdutoRN produtoRN;
	
	@Inject
	private AvaliacaoProdutoRN avaliacaoProdutoRN;
	
	@Inject
	private DepositoRN depositoRN;
	
	@Inject
	private CentroRN centroRN;
	
	@Inject
	private ItemEstoqueRN itemEstoqueRN;
	
	private List<ItemEstoqueNaoCadastrado> itensNaoCadastrados = new ArrayList<ItemEstoqueNaoCadastrado>();

	public void salvar(Estoque estoque) throws RNException {
		try {
			estoqueDAO.salvar(estoque); 
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}

	public Estoque buscarPorCodigo(Long codigo) {
		return estoqueDAO.buscarPorCodigo(codigo);
	}
	
	public Estoque buscarPorCentro(Centro centro) {
		return estoqueDAO.buscarPorCentro(centro);
	}

	public List<Estoque> listar() {
		return estoqueDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException {

		itemEstoqueRN.limpar();
		
		try {
			ItemEstoque itemEstoque;

			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Integer quantidade = 0;
				Double valor = 0.0;
				Integer produto = 0;

				itemEstoque = new ItemEstoque();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						itemEstoque.setEstoque(this.buscarPorCentro(
								centroRN.buscarPorDescricao(cell.getStringCellValue())));
						break;
					
					case 1:
						produto = new Double(cell.getNumericCellValue()).intValue();
						itemEstoque.setProduto(produtoRN.buscarPorCodigoSAP(produto));
						break;

					case 2:
						itemEstoque.setDeposito(depositoRN.buscarPorDescricao(cell.getStringCellValue()));
						break;

					case 3:
						itemEstoque.setAvaliacaoProduto(
								avaliacaoProdutoRN.buscarPorDescricao(cell.getStringCellValue()));
						break;

					case 4:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 5:
						valor += cell.getNumericCellValue();
						break;

					case 6:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 7:
						valor += cell.getNumericCellValue();
						break;

					case 8:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 9:
						valor += cell.getNumericCellValue();
						break;

					case 10:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 11:
						valor += cell.getNumericCellValue();
						break;

					case 12:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 13:
						valor += cell.getNumericCellValue();
						break;

					case 14:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 15:
						valor += cell.getNumericCellValue();
						break;

					case 16:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 17:
						valor += cell.getNumericCellValue();
						break;

					case 18:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 19:
						valor += cell.getNumericCellValue();
						break;

					case 20:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 21:
						valor += cell.getNumericCellValue();
						break;

					case 22:
						quantidade += (int) cell.getNumericCellValue();
						itemEstoque.setSaldo(quantidade);
						break;

					case 23:
						valor += cell.getNumericCellValue();
						itemEstoque.setValorUnitario(valor / quantidade);
						break;

					} // Fim do switch
				} // Fim do while cellIterator

				String validacao = validarCadastro(itemEstoque);
				if(validacao == null){
					itemEstoqueRN.salvar(itemEstoque);
				} else {
					itensNaoCadastrados.add(
							new ItemEstoqueNaoCadastrado(produto.toString(), validacao));
				}
			} // Fim do while excel.getRows
			
			EstoqueLog.registrarLog(itensNaoCadastrados);
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso.");
			
		} catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
	
	public String validarCadastro(ItemEstoque itemEstoque){
		
		if(itemEstoque.getAvaliacaoProduto() == null){
			return "Tipo de avaliação inválido.";
		} else {
			if(itemEstoque.getDeposito() == null){
				return "Depósito inválido.";
			} else {
				if(itemEstoque.getEstoque() == null){
					return "Estoque inválido.";
				} else {
					if(itemEstoque.getProduto() == null){
						return "Produto inválido.";
					} else {
						return null;
					}
				}
			}
		}
	}

	public List<ItemEstoqueNaoCadastrado> getItensNaoCadastrados() {
		return itensNaoCadastrados;
	}

	public void setItensNaoCadastrados(List<ItemEstoqueNaoCadastrado> itensNaoCadastrados) {
		this.itensNaoCadastrados = itensNaoCadastrados;
	}
}
