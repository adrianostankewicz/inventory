package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.SetorAtividadeDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.SetorAtividade;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class SetorAtividadeRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorAtividadeDAO setorAtividadeDAO;
	
	public void salvar(SetorAtividade setorAtividade) throws RNException{
		try{
			setorAtividadeDAO.salvar(setorAtividade);
			
		}catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public SetorAtividade buscarPorCodigo(Long codigo){
		return setorAtividadeDAO.buscarPorCodigo(codigo);
	}
	
	public SetorAtividade buscarPorCodigoSAP(String codigoSAP){
		return setorAtividadeDAO.buscarPorCodigoSAP(codigoSAP);
	}
	
	public SetorAtividade buscarPorDescricao(String descricao){
		return setorAtividadeDAO.buscarPorDescricao(descricao);
	}
	
	public List<SetorAtividade> listarTodos(){
		return setorAtividadeDAO.listarTodos();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		try{
			SetorAtividade setorAtividade;
			
			while (excel.getRows().hasNext()) { // Leitura das linhas.
	
				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();
	
				setorAtividade = new SetorAtividade();
	
				while (cellIterator.hasNext()) { //Leitura das colunas.
	
					Cell cell = cellIterator.next();
	
					switch (cell.getColumnIndex()) {
	
						case 0: //Coluna A do arquivo Excel
							setorAtividade.setCodigoSAP(cell.getStringCellValue());
							break;
							
						case 1: //Coluna B do arquivo Excel
							setorAtividade.setDescricao(cell.getStringCellValue());
							break;
							
					} // Fim do switch
				}// Fim do while cellIterator
				
				this.salvar(setorAtividade);
			} // Fim do while excel.getRows
			
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso!");
			
		}catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
}
