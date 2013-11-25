package com.master.android.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.master.android.R;
import com.master.android.util.Constantes;
import com.master.android.util.RequisicaoWeb;

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
	
	private void cadastrar(){
		
		String nome = txtNome.getText().toString();
		String cpf = txtCpf.getText().toString();
		String email = txtEmail.getText().toString();
		String senha = txtSenha.getText().toString();
		String reSenha = txtReSenha.getText().toString();

		if(!senha.equals("") && !reSenha.equals("") && senha.equals(reSenha)){
			
			JSONObject usuario = new JSONObject();
			try {
				usuario.put("nome", nome);
				usuario.put("cpf", cpf);
				usuario.put("login", email);
				usuario.put("senha", senha);
				
				String link = Constantes.SERVIDOR + "acao=cadastrar&json=" + usuario;
				RequisicaoWeb req = new RequisicaoWeb(this);
				req.execute(link);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}	
}
