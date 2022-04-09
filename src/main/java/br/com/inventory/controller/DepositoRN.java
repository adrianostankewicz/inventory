package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.DepositoDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Deposito;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class DepositoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DepositoDAO depositoDAO;
	
	public void salvar(Deposito deposito) throws RNException{
		try{
			depositoDAO.salvar(deposito);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}

	public Deposito buscarPorCodigo(Long codigo){
		return depositoDAO.buscarPorCodigo(codigo);
	}
	
	public Deposito buscarPorDescricao(String descricao){
		return depositoDAO.buscarPorDescricao(descricao);
	}
	
	public List<Deposito> listar(){
		return depositoDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		try{
			Deposito deposito;

			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();

				deposito = new Deposito();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						deposito.setDescricao(cell.getStringCellValue());
						break;

					case 1:
						deposito.setTextoBreve(cell.getStringCellValue());
						break;
						
					} // Fim do switch
				}// Fim do while cellIterator
				
				this.salvar(deposito);
			} // Fim do while excel.getRows
			
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso!");
			
		} catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
}
