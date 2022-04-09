package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.GrupoMercadoriaDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.GrupoMercadoria;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class GrupoMercadoriaRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoMercadoriaDAO grupoMercadoriaDAO;
	
	public void salvar(GrupoMercadoria grupoMercadoria) throws RNException{
		try{
			grupoMercadoriaDAO.salvar(grupoMercadoria);
			
		} catch(DAOException de){
			throw new RNException(de);
		}
	}
	
	public GrupoMercadoria buscarPorCodigo(Long codigo){
		return grupoMercadoriaDAO.buscarPorCodigo(codigo);
	}
	
	public GrupoMercadoria buscarPorCodigoSAP(String codigoSAP){
		return grupoMercadoriaDAO.buscarPorCodigoSAP(codigoSAP);
	}
	
	public GrupoMercadoria buscarPorDescricao(String descricao){
		return grupoMercadoriaDAO.buscarPorDescricao(descricao);
	}
	
	public List<GrupoMercadoria> listar(){
		return grupoMercadoriaDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		GrupoMercadoria gm;
		
		try{
			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();

				gm = new GrupoMercadoria();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						gm.setCodigoSAP(cell.getStringCellValue());
						break;

					case 1:
						gm.setDescricao(cell.getStringCellValue());
						break;
				
					} // Fim do switch

				}// Fim do while cellIterator
				this.salvar(gm);

			} // Fim do while excel.getRows
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso.");
		
		} catch(RNException re){
			throw new RNException(re.getMessage());
		}
	}
}
