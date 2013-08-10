package com.master.android.cadastro;

import masterfila.entidade.Usuario;

import com.master.android.repositorio.RepositorioUsuario;

public class CadastroUsuario {

	private RepositorioUsuario rep;
	
	public CadastroUsuario(RepositorioUsuario rep){
		this.rep = rep;
	}
	
	public Usuario logar(String email, String senha){
		
		Usuario retorno = rep.logar(email, senha);
		return retorno;
	}
}
