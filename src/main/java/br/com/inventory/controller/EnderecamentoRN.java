package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.EnderecamentoDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Enderecamento;
import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.model.estoque.Estoque;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class EnderecamentoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecamentoDAO enderecamentoDAO;
	
	@Inject
	private ProdutoRN produtoRN;
	
	@Inject
	private EnderecoRN enderecoRN;
	
	@Inject
	private DepositoRN depositoRN;
	
	@Inject
	private EstoqueRN estoqueRN;
	
	@Inject
	private CentroRN centroRN;
	
	public void salvar(Enderecamento enderecamento) throws RNException{
		try{
			enderecamentoDAO.salvar(enderecamento);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public Enderecamento buscarPorCodigo(Long codigo){
		return enderecamentoDAO.buscarPorCodigo(codigo);
	}
	
	public List<Enderecamento> buscarPorEndereco(Endereco endereco){
		return enderecamentoDAO.buscarPorEndereco(endereco);
	}
	
	public List<Enderecamento> consolidarSaldoPorProduto(Estoque estoque){
		return enderecamentoDAO.consolidarSaldoPorProduto(estoque);
	}
	
	public List<Enderecamento> listarPorEstoque(Estoque estoque){
		return enderecamentoDAO.listarPorEstoque(estoque);
	}
	
	public List<Enderecamento> listarPorProduto(Produto produto){
		return  enderecamentoDAO.listarPorProduto(produto);
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		try {
			Enderecamento enderecamento;

			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Integer quantidade = 0;

				enderecamento = new Enderecamento();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						enderecamento.setProduto(produtoRN.buscarPorCodigoSAP(
													new Double(cell.getNumericCellValue()).intValue()));
						break;

					case 2:
						enderecamento.setEndereco(enderecoRN.buscarPorDescricao(cell.getStringCellValue()));
						break;

					case 4:
						quantidade += (int) cell.getNumericCellValue();
						break;

					case 5:
						quantidade += (int) cell.getNumericCellValue();
						enderecamento.setSaldo(quantidade);
						break;
						
					case 7:
						enderecamento.setDeposito(depositoRN.buscarPorDescricao(cell.getStringCellValue()));
						break;
						
					case 8:
						enderecamento.setEstoque(estoqueRN.buscarPorCentro(
													centroRN.buscarPorDescricao(cell.getStringCellValue())));

					} // Fim do switch
				} // Fim do while cellIterator
				
				this.salvar(enderecamento);
			} // Fim do while excel.getRows
				
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso!");	
				
		} catch (RNException re) {
			throw new RNException(re.getMessage());
		}
	}
}
