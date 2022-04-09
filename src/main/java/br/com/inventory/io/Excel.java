package br.com.inventory.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.inventory.util.UtilException;

public class Excel {

	private Workbook workbook;
	private Sheet sheet;
	private Iterator <Row> rowIterator;

	public void upload(InputStream file) throws UtilException{

		try{
			if (file != null) {
				workbook = WorkbookFactory.create(file);
				sheet = workbook.getSheetAt(0);
				rowIterator = sheet.iterator();
			}
		} catch(IOException | InvalidFormatException e){
			throw new UtilException("Erro ao processar o arquivo. Erro: " + e.getMessage());
		}
	}
	
	public void download(File file) throws UtilException{

		try{
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
		}catch(IOException e){
			throw new UtilException("Erro ao processar o arquivo. Erro: " + e.getMessage());
		}
	}
	
	public void close() throws UtilException{
		try {
			workbook.close();
		} catch (IOException e) {
			throw new UtilException("Erro ao fechar o arquivo. Erro: " + e.getMessage());
		}
	}
	
	public Sheet addSheet(String nome){
		Sheet sheet = workbook.createSheet(nome);
		sheet.setSelected(true);
		return sheet;
	}
	
	public Workbook getWorkbook(){
		return workbook;
	}
	
	public Sheet getSheet(){
		return sheet;
	}
	
	public Iterator<Row> getRows(){
		return rowIterator;
	}
}
