package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.CentroDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.Centro;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class CentroRN implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CentroDAO centroDAO;
	
	public void salvar(Centro centro) throws RNException{
		try{
			centroDAO.salvar(centro);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public Centro buscarPorCodigo(Long codigo){
		return centroDAO.buscarPorCodigo(codigo);
	}
	
	public Centro buscarPorDescricao(String descricao){
		return centroDAO.buscarPorDescricao(descricao);
	}
	
	public List<Centro> listar(){
		return centroDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		try{
			Centro centro;
			
			while (excel.getRows().hasNext()) { // Leitura das linhas.
	
				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();
	
				centro = new Centro();
	
				while (cellIterator.hasNext()) { //Leitura das colunas.
	
					Cell cell = cellIterator.next();
	
					switch (cell.getColumnIndex()) {
	
						case 0: //Coluna A do arquivo Excel
							centro.setDescricao(cell.getStringCellValue());
							break;
							
						case 1: //Coluna B do arquivo Excel
							centro.setLocal(cell.getStringCellValue());
							break;
							
					} // Fim do switch
				}// Fim do while cellIterator
				
				this.salvar(centro);
			} // Fim do while excel.getRows
			
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso!");
			
		}catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
}
