package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.HierarquiaDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.Hierarquia;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class HierarquiaRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HierarquiaDAO hierarquiaDAO;

	public void salvar(Hierarquia hierarquia) throws RNException{
		try{
			hierarquiaDAO.salvar(hierarquia);
			
		} catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public Hierarquia buscarPorCodigo(Long codigo){
		return hierarquiaDAO.buscarPorCodigo(codigo);
	}
	
	public Hierarquia buscarPorCodigoSAP(String codigoSAP){
		return hierarquiaDAO.buscarPorCodigoSAP(codigoSAP);
	}
	
	public Hierarquia buscarPorDescricao(String descricao){
		return hierarquiaDAO.buscarPorDescricao(descricao);
	}
	
	public List<Hierarquia> listar(){
		return hierarquiaDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		Hierarquia hierarquia;
		
		try{
			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();

				hierarquia = new Hierarquia();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						hierarquia.setCodigoSAP(cell.getStringCellValue());
						break;

					case 1:
						hierarquia.setDescricao(cell.getStringCellValue());
						break;
				
					} // Fim do switch
				}// Fim do while cellIterator
				
				this.salvar(hierarquia);
			} // Fim do while excel.getRows

			FacesUtil.addSuccessMessage("Arquivo processado com sucesso."); 
			
		}catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
}
