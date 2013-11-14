package com.master.android.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.master.android.R;

@SuppressLint("NewApi")
public class CadastroActivity extends Activity implements OnClickListener {
	
	private EditText txtNome;
	private EditText txtCpf;
	private EditText txtEmail;
	private EditText txtSenha;
	private EditText txtReSenha;
	private Button btVoltarLogin;
	private Button btCadastrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_cadastro);
		
		txtNome = (EditText) findViewById(R.id.txtNome);
		txtCpf = (EditText) findViewById(R.id.txtCpf);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		txtReSenha = (EditText) findViewById(R.id.txtSenha);
		
		btVoltarLogin = (Button) findViewById(R.id.btVoltarLogin);
		btVoltarLogin.setOnClickListener(this);
		
		btCadastrar = (Button) findViewById(R.id.btCadastrar);
		btCadastrar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btCadastrar){
			cadastrar();
		}
		else if(v.getId() == R.id.btVoltarLogin){
			finish();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	private void cadastrar(){
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		String nome = txtNome.getText().toString();
		String cpf = txtCpf.getText().toString();
		String email = txtEmail.getText().toString();
		String senha = txtSenha.getText().toString();
		String reSenha = txtReSenha.getText().toString();
		
		if(!senha.equals("") && !reSenha.equals("") && senha.equals(reSenha)){
			
			try{
				
				JSONObject usuario = new JSONObject();
				usuario.put("nome", nome);
				usuario.put("cpf", cpf);
				usuario.put("email", email);
				usuario.put("senha", senha);
				
				
				String link = "http://10.0.2.2:8080/MasterFila/controlador?acao=cadastrar&json=" + usuario;
				URL url = new URL(link);
				HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

				conexao.setDoOutput(false);
				conexao.setDoInput(true);
				conexao.setRequestMethod("GET");

				if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
					Toast.makeText(this, "Cadastrado com Sucesso.", Toast.LENGTH_SHORT).show();
					setResult(RESULT_OK);
					limparCampos();
				}
				else{
					Toast.makeText(this, "Erro ao Tentar Cadastrar", Toast.LENGTH_SHORT).show();					
				}				
				
			}catch(JSONException e){
				e.printStackTrace();
			} catch (ProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void limparCampos(){
		txtCpf.setText("");
		txtEmail.setText("");
		txtNome.setText("");
		txtReSenha.setText("");
		txtSenha.setText("");
	}
}
