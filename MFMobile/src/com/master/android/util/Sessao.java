package com.master.android.util;

import com.master.android.entidade.Estabelecimento;
import com.master.android.entidade.Usuario;

public class Sessao {

	private static Usuario logado;
	private static Estabelecimento escolhido;
	
	public static void logar(Usuario u){
		logado = u;
	}
	
	public static Usuario logado(){
		return logado;
	}
	
	public static void deslogar(){
		logado = null;
		escolhido = null;
	}
	
	public static void escolherEstabelecimento(Estabelecimento est){
		escolhido = est;
	}
	
	public static Estabelecimento escolhido(){
		return escolhido;
	}
}
