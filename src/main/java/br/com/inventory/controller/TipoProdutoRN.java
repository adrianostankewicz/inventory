package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.TipoProdutoDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.TipoProduto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class TipoProdutoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoProdutoDAO tipoProdutoDAO;
	
	public void salvar(TipoProduto tipoProduto) throws RNException {
		try{
			tipoProdutoDAO.salvar(tipoProduto);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public TipoProduto buscarPorCodigo(Long codigo){
		return tipoProdutoDAO.buscarPorCodigo(codigo);
	}
	
	public TipoProduto buscarPorCodigoSAP(String codigoSAP){
		return tipoProdutoDAO.buscarPorCodigoSAP(codigoSAP);
	}
	
	public List<TipoProduto> listar(){
		return tipoProdutoDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		try{
			TipoProduto tipoProduto;
			
			while (excel.getRows().hasNext()) { // Leitura das linhas.
	
				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();
	
				tipoProduto = new TipoProduto();
	
				while (cellIterator.hasNext()) { //Leitura das colunas.
	
					Cell cell = cellIterator.next();
	
					switch (cell.getColumnIndex()) {
	
						case 0: //Coluna A do arquivo Excel
							tipoProduto.setCodigoSAP(cell.getStringCellValue());
							break;
							
						case 1: //Coluna B do arquivo Excel
							tipoProduto.setDescricao(cell.getStringCellValue());
							break;
							
					} // Fim do switch
				}// Fim do while cellIterator
				
				this.salvar(tipoProduto);
			} // Fim do while excel.getRows
			
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso!");
			
		}catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
}
