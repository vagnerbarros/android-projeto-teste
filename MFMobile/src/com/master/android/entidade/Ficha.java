package com.master.android.entidade;

public class Ficha {

	private Usuario cliente;
	private String numero;
	private String hora;
	
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String toString(){
		return numero + "   			   " + hora;
	}
}
