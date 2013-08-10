package com.master.android.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.content.ContextWrapper;

import com.master.android.util.GerenteConexao;

public class RepositorioUsuario {

	private ContextWrapper context;
	
	public RepositorioUsuario(ContextWrapper context){
		this.context = context;
	}
	
	public Usuario logar(String email, String senha){
		String sql = "select * from usuario where email like '"+ email + "' AND senha like '" + senha + "'";
		Usuario u = null;

		try{
			Statement st = GerenteConexao.getConexao().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				u = montarObjeto(rs);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return u;
	}
	
	
//	public void adicionarUsuario(Aluno u){
//		String sql = "insert into usuario( id, nome, cpf, rg, sexo, data_nascimento, endereco, cidade, estado, email, senha, perfil) values(?,?,?,?,?,?,?,?,?,?,?,?)";
//
//		try {
//			PreparedStatement ps = GerenteConexao.getConexao().prepareStatement(sql);
//			montarOperacao(ps, u);
//
//			ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public void removerUsuario(Aluno u){
//
//		String sql = "delete from usuario where id = ?";
//		try{
//			PreparedStatement ps = GerenteConexao.getConexao().prepareStatement(sql);
//			ps.setLong(1, u.getId());
//			ps.executeUpdate();
//
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	public List<Aluno> listarUsuario(){
//		String sql = "Select * from usuario";
//		List<Aluno> retorno = null;
//		try {
//			Statement st = GerenteConexao.getConexao().createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			retorno = new ArrayList<Aluno>();
//
//			while(rs.next()){
//				Aluno u = montarObjeto(rs);
//				retorno.add(u);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return retorno;
//	}
//
//	public void editarUsuario(Aluno u){
//		String sql = "update usuario set id=?, nome =?, cpf =?, rg =?, sexo =?," +
//				" data_nascimento =?, endereco=?, cidade =?, estado =?, email =?, senha =?" +
//				", perfil =? where id ="+u.getId();
//
//		try {
//			PreparedStatement ps = GerenteConexao.getConexao().prepareStatement(sql);
//			montarOperacao(ps, u);
//			
//			ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Aluno buscarUsuarioPorId(Long id){
//		String sql = "select * from usuario where id = "+ id;
//		Aluno u = null;
//
//		try{
//			Statement st = GerenteConexao.getConexao().createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			if(rs.next()){
//				u = montarObjeto(rs);
//			}
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return u;
//	}
//	
//	
//	
//	public List<Aluno> buscarLike(String campo, String valor){
//		String sql = "Select * FROM usuario WHERE " + campo + " like '%" + valor + "%'";
//
//		List<Aluno> retorno = null;
//		try {
//			Statement st = GerenteConexao.getConexao().createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			retorno = new ArrayList<Aluno>();
//
//			while(rs.next()){
//				Aluno u = montarObjeto(rs);
//				retorno.add(u);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return retorno;
//	}
//	
	private Usuario montarObjeto(ResultSet rs){
		Usuario u = new Usuario();
		try {
			u.setId(rs.getLong("id"));
			u.setNome(rs.getString("nome"));
			u.setCpf(rs.getString("cpf"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setTelefone(rs.getString("telefone"));
			u.setRua(rs.getString("rua"));
			u.setNumero(rs.getString("numero"));
			u.setBairro(rs.getString("bairro"));
			u.setCidade(rs.getString("cidade"));
			u.setEstado(rs.getString("estado"));
			u.setCep(rs.getString("cep"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	private void montarOperacao(PreparedStatement ps, Usuario u){

		try {
			ps.setLong(1, u.getId());
			ps.setString(2, u.getNome());
			ps.setString(3, u.getCpf());
			ps.setString(9, u.getEstado());
			ps.setString(11, u.getSenha());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
