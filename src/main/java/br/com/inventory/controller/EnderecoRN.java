package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.EnderecoDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.estoque.Endereco;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class EnderecoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	public void salvar(Endereco endereco) throws RNException{
		try{
			enderecoDAO.salvar(endereco);
			
		} catch(DAOException de){
			throw new RNException(de);
		}
	}

	public Endereco buscarPorCodigo(Long codigo){
		return enderecoDAO.buscarPorCodigo(codigo);
	}
	
	public Endereco buscarPorDescricao(String descricao){
		return enderecoDAO.buscarPorDescricao(descricao);
	}
	
	public List<Endereco> listar(){
		return enderecoDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		try {
			
			Endereco endereco;

			while (excel.getRows().hasNext()) {

				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();

				endereco = new Endereco();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {

					case 0:
						endereco.setDescricao(cell.getStringCellValue());
						break;

					case 1:
						endereco.setRua(cell.getStringCellValue());
						break;

					case 2:
						endereco.setModulo(cell.getStringCellValue());
						break;

					case 3:
						endereco.setNivel(cell.getStringCellValue());
						break;

					case 4:
						endereco.setVao(cell.getStringCellValue());
						break;

					case 5:
						if (cell.getStringCellValue().equalsIgnoreCase("Picking")) {
							endereco.setPicking(true);
							break;
						} else {
							endereco.setPicking(false);
							break;
						}

					case 6:
						endereco.setUnidadeAcondicionamento(cell.getStringCellValue());
						break;

					} // Fim do switch
				} // Fim do while cellIterator
				
				this.salvar(endereco);
			} // Fim do while excel.getRows
				
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso!");	
				
		} catch (RNException re) {
			throw new RNException(re.getMessage());
		}
	}
}
