package br.com.inventory.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import br.com.inventory.model.estoque.ItemEstoqueNaoCadastrado;
import br.com.inventory.util.RNException;

public class EstoqueLog implements Serializable {
	private static final long serialVersionUID = 1L;

	public static File registrarLog(List<ItemEstoqueNaoCadastrado> itensNaoCadastrados) throws RNException{
		try {
			if(!itensNaoCadastrados.isEmpty()){
				File file = new File(System.getProperties().getProperty("user.home")+"/desktop/Produtos_não_cadastrados.txt");
				PrintWriter pw = new PrintWriter(file);
				
				for(ItemEstoqueNaoCadastrado ie : itensNaoCadastrados){
					pw.println(ie.getItem());
				}
				
				pw.close();
				return file;
			}
			return null;
			
		} catch (FileNotFoundException e) {
			throw new RNException(e.getMessage());
		}
	}
}
