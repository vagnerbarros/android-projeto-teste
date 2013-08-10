package com.master.android.fachada;

import com.master.android.cadastro.CadastroUsuario;
import com.master.android.repositorio.RepositorioUsuario;

import android.content.ContextWrapper;

public class Fachada {

	private static Fachada instancia;
	private CadastroUsuario cadUsuario; 
	
	private Fachada(ContextWrapper context){
		iniciar(context);
	}
	
	private void iniciar(ContextWrapper context){
		
		RepositorioUsuario usuario = new RepositorioUsuario(context);
		cadUsuario = new CadastroUsuario(usuario);
	}
	
	public static Fachada getInstancia(ContextWrapper context){
		if(instancia == null){
			instancia = new Fachada(context);
		}
		return instancia;
	}
	
	public CadastroUsuario cadastroUsuario(){
		return cadUsuario;
	}
}