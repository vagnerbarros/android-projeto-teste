package com.master.android.dominio;

import java.util.ArrayList;
import java.util.List;

import com.master.android.R;

public class Categoria {

	public static String CONSULTORIO = "Consultórios Médicos";
	public static String REPARTICAO = "Repartições Públicas";
	public static String AGENCIA = "Agências Bancárias";
	public static String CARTORIO = "Cartórios";
	public static String PREFEITURA = "Prefeitura e setores vinculados";
	public static String OUTRO = "Outros";
	
	public static int ICONE_CONSULTORIO = R.drawable.icon_consultorio;
	public static int ICONE_REPARTICAO = R.drawable.icon_reparticoes;
	public static int ICONE_AGENCIA = R.drawable.icon_bancos;
	public static int ICONE_CARTORIO = R.drawable.icon_cartorios;
	public static int ICONE_PREFEITURA = R.drawable.icon_prefeitura;
	public static int ICONE_OUTRO = R.drawable.icon_outros;
	
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
	
	public static int getIconeCategoria(String categoria){
		if(categoria.equals(AGENCIA)){
			return ICONE_AGENCIA;
		}
		else if(categoria.equals(CARTORIO)){
			return ICONE_CARTORIO;
		}
		else if(categoria.equals(CONSULTORIO)){
			return ICONE_CONSULTORIO;
		}
		else if(categoria.equals(PREFEITURA)){
			return ICONE_PREFEITURA;
		}
		else if(categoria.equals(REPARTICAO)){
			return ICONE_REPARTICAO;
		}
		else{
			return ICONE_OUTRO;
		}
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
	
	public static String [] categorias(){
		return new String [] {CONSULTORIO, REPARTICAO, AGENCIA, CARTORIO, PREFEITURA};
	}
}
