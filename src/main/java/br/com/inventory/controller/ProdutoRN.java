package br.com.inventory.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.inventory.dao.ProdutoDAO;
import br.com.inventory.io.Excel;
import br.com.inventory.model.produto.Produto;
import br.com.inventory.util.DAOException;
import br.com.inventory.util.FacesUtil;
import br.com.inventory.util.RNException;

public class ProdutoRN implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@Inject
	private GrupoMercadoriaRN grupoMercadoriaRN;
	
	@Inject
	private HierarquiaRN hierarquiaRN;
	
	@Inject
	private FabricanteRN fabricanteRN;
	
	@Inject
	private TipoProdutoRN tipoProdutoRN;
	
	@Inject
	private SetorAtividadeRN setorAtividadeRN;

	public void salvar(Produto produto) throws RNException{
		try{
			if(produtoDAO.buscarPorCodigoSAP(produto.getCodigoSAP()) == null){
				produtoDAO.salvar(produto);
			}
			
		}catch(DAOException de){
			throw new RNException(de.getMessage());
		}
	}
	
	public Produto buscarPorCodigo(Long codigo) {
		return produtoDAO.buscarPorCodigo(codigo);
	}
	
	public Produto buscarPorCodigoSAP(Integer codigoSAP) {
		return produtoDAO.buscarPorCodigoSAP(codigoSAP);
	}
	
	public List<Produto> listar(){
		return produtoDAO.listar();
	}
	
	public void salvarPorExcel(Excel excel) throws RNException{
		
		Produto produto;
		String linha = "";
		String coluna = "";
		
		try{
			while (excel.getRows().hasNext()) { // Leitura das linhas.
	
				Row row = excel.getRows().next();
				Iterator<Cell> cellIterator = row.cellIterator();
	
				produto = new Produto();
	
				while (cellIterator.hasNext()) { // Leitura das colunas.
	
					Cell cell = cellIterator.next();
					linha = String.valueOf(cell.getRowIndex());
					coluna = String.valueOf(cell.getColumnIndex());
	
					switch (cell.getColumnIndex()) {
	
					case 0:
						produto.setCodigoSAP(new Double(cell.getNumericCellValue()).intValue());
						break;
	
					case 1:
						produto.setDescricao(cell.getStringCellValue());
						break;
	
					case 2:
						produto.setPartNumber(cell.getStringCellValue());
						break;
	
					case 3:
						produto.setPeso(cell.getNumericCellValue());
						break;
	
					case 4:
						produto.setEan(new Double(cell.getNumericCellValue()).longValue());
						break;
	
					case 5:
						produto.setGrupoMercadoria(grupoMercadoriaRN.buscarPorCodigoSAP(cell.getStringCellValue()));
						break;
					
					case 6:
						produto.setHierarquia(hierarquiaRN.buscarPorCodigoSAP(cell.getStringCellValue()));
						break;
	
					case 7:
						produto.setFabricante(fabricanteRN.buscarPorNome(cell.getStringCellValue()));
						break;

					case 8:
						produto.setTipoProduto(tipoProdutoRN.buscarPorCodigoSAP(cell.getStringCellValue()));
						break;
						
					case 9:
						produto.setSetorAtividade(setorAtividadeRN.buscarPorCodigoSAP(cell.getStringCellValue()));
						break;
					
					case 10:
						produto.setAltura(cell.getNumericCellValue());
						break;
						
					case 11:
						produto.setLargura(cell.getNumericCellValue());
						break;
						
					case 12:
						produto.setComprimento(cell.getNumericCellValue());
						break;

					case 13:
						produto.setSerialNumber((cell.getNumericCellValue()) == 0 ? false : true);
						break;
						
					case 14:
						produto.setCaixaMaster(new Double(cell.getNumericCellValue()).longValue());
						break;
						
					case 15:
						produto.setPaletizacao(new Double(cell.getNumericCellValue()).longValue());
						break;
	
					} // Fim do switch
				}// Fim do while cellIterator
				
				Double volume = produto.getAltura() * produto.getComprimento() * produto.getLargura();
				produto.setVolume(volume);
				
				this.salvar(produto);
			} // Fim do while excel.getRows
			
			FacesUtil.addSuccessMessage("Arquivo processado com sucesso.");
		}catch(RNException re){
			throw new RNException(re.getMessage() + ". Linha: " + linha + "; Coluna: " + coluna);
		}
	}
}
