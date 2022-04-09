package br.com.inventory.model.estoque;

import java.io.Serializable;

public class ItemEstoqueNaoCadastrado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String item;
	private String messagem;
	
	public ItemEstoqueNaoCadastrado(String item, String messagem) {
		this.item = item;
		this.messagem = messagem;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public String getMessagem() {
		return messagem;
	}
	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemEstoqueNaoCadastrado other = (ItemEstoqueNaoCadastrado) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
}
