package com.master.android.dominio;

import java.util.ArrayList;
import java.util.List;

import com.master.android.R;

public class Categoria {

	private static String CONSULTORIO = "Consultórios Médicos";
	private static String REPARTICAO = "Repartições Públicas";
	private static String AGENCIA = "Agências Bancárias";
	private static String CARTORIO = "Cartórios";
	private static String PREFEITURA = "Prefeitura e setores vinculados";
	private static String OUTRO = "Outros";
	
	private static int ICONE_CONSULTORIO = R.drawable.icon_consultorio;
	private static int ICONE_REPARTICAO = R.drawable.icon_reparticoes;
	private static int ICONE_AGENCIA = R.drawable.icon_bancos;
	private static int ICONE_CARTORIO = R.drawable.icon_cartorios;
	private static int ICONE_PREFEITURA = R.drawable.icon_prefeitura;
	private static int ICONE_OUTRO = R.drawable.icon_outros;
	
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
