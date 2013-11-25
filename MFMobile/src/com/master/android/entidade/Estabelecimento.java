package com.master.android.entidade;

import com.master.android.R;

public class Estabelecimento {

	private static int ICONE_OUTRO = R.drawable.icon_outros;
	private String id;
	private String nome;
	private int icone;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setIcone(int icone){
		this.icone = icone;
	}
	
	public int getIcone(){
		return icone;
	}
}
