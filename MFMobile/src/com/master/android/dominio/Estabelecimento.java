package com.master.android.dominio;

import java.util.ArrayList;
import java.util.List;

import com.master.android.R;

public class Estabelecimento {

	private static int ICONE_OUTRO = R.drawable.icon_outros;
	
	private String nome;
	private int icone;
	
	public Estabelecimento(String nome, int icone){
		this.nome = nome;
		this.icone = icone;
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
	
	public static List<Estabelecimento> getEstabelecimentos(){
		
		List<Estabelecimento> retorno = new ArrayList<Estabelecimento>();
		
		Estabelecimento e1 = new Estabelecimento("Estabelecimento 01", ICONE_OUTRO);
		Estabelecimento e2 = new Estabelecimento("Estabelecimento 02", ICONE_OUTRO);
		Estabelecimento e3 = new Estabelecimento("Estabelecimento 03", ICONE_OUTRO);
		Estabelecimento e4 = new Estabelecimento("Estabelecimento 04", ICONE_OUTRO);
		
		retorno.add(e1);
		retorno.add(e2);
		retorno.add(e3);
		retorno.add(e4);
		
		return retorno;
	}
}
