package com.master.android.dominio;

import java.util.ArrayList;
import java.util.List;

import android.R;

public class Categoria {

	private static String CONSULTORIO = "Consultório Médico";
	private static String REPARTICAO = "Repartição Pública";
	private static String AGENCIA = "Agência Bancária";
	private static String CARTORIO = "Cartório";
	private static String PREFEITURA = "Prefeitura e setores vinculados";
	private static String OUTRO = "Outros";
	
	private static int ICONE_CONSULTORIO = R.drawable.btn_default;
	private static int ICONE_REPARTICAO = R.drawable.btn_default;
	private static int ICONE_AGENCIA = R.drawable.btn_default;
	private static int ICONE_CARTORIO = R.drawable.btn_default;
	private static int ICONE_PREFEITURA = R.drawable.btn_default;
	private static int ICONE_OUTRO = R.drawable.btn_default;
	
	private String nome;
	private int icone;
	
	public Categoria(String nome, int icone){
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
	
	public static List<Categoria> getCategorias(){
		
		Categoria consultorio = new Categoria(CONSULTORIO, ICONE_CONSULTORIO);
		Categoria reparticao = new Categoria(REPARTICAO, ICONE_REPARTICAO);
		Categoria agencia = new Categoria(AGENCIA, ICONE_AGENCIA);
		Categoria cartorio = new Categoria(CARTORIO, ICONE_CARTORIO);
		Categoria prefeitura = new Categoria(PREFEITURA, ICONE_PREFEITURA);
		Categoria outro = new Categoria(OUTRO, ICONE_OUTRO);
		
		List<Categoria> retorno = new ArrayList<Categoria>();
		retorno.add(consultorio);
		retorno.add(reparticao);
		retorno.add(agencia);
		retorno.add(cartorio);
		retorno.add(prefeitura);
		retorno.add(outro);
		
		return retorno;
	}
}
