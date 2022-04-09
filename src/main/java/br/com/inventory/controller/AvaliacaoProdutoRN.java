package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.AvaliacaoProdutoDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.AvaliacaoProduto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.RNException;

public class AvaliacaoProdutoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoProdutoDAO avaliacaoProdutoDAO;
	
	public void salvar(AvaliacaoProduto avaliacaoProduto) throws RNException{
		try{
			avaliacaoProdutoDAO.salvar(avaliacaoProduto);
			
		} catch(DAOException de){
			throw new RNException(de);
		}
	}
	
	public AvaliacaoProduto buscarPorCodigo(Long codigo){
		return avaliacaoProdutoDAO.buscarPorCodigo(codigo);
	}
	
	public AvaliacaoProduto buscarPorDescricao(String descricao){
		return avaliacaoProdutoDAO.buscarPorDescricao(descricao);
	}
	
	public List<AvaliacaoProduto> listar(){
		return avaliacaoProdutoDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException {

		try {
			AvaliacaoProduto ap;

			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();

				ap = new AvaliacaoProduto();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						ap.setDescricao(cell.getStringCellValue());
						break;

					} // Fim do switch
				}// Fim do while cellIterator

				this.salvar(ap);
			} // Fim do while excel.getRows
			
		} catch (RNException re) {
			throw new RNException(re.getMessage());
		}
	}

}
