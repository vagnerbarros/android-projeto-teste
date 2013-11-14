package com.master.android.fachada;

import android.content.ContextWrapper;

public class Fachada {

	private static Fachada instancia;
	
	private Fachada(ContextWrapper context){
		iniciar(context);
	}
	
	private void iniciar(ContextWrapper context){
		
	}
	
	public static Fachada getInstancia(ContextWrapper context){
		if(instancia == null){
			instancia = new Fachada(context);
		}
		return instancia;
	}
}