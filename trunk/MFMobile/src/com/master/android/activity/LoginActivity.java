package com.master.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.master.android.R;

public class LoginActivity extends Activity implements OnClickListener {
	
	private EditText txtEmail;
	private EditText txtSenha;
	private Button btCriarConta;
	private Button btConectar;
	private TextView txBtEsqueciSenha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_login);
		
		txBtEsqueciSenha = (TextView) findViewById(R.id.textViewEsqueciSenha);
		txBtEsqueciSenha.setOnClickListener(this);
		
		btCriarConta = (Button) findViewById(R.id.btCriarConta);
		btCriarConta.setOnClickListener(this);
		
		btConectar = (Button) findViewById(R.id.btConectar);
		btConectar.setOnClickListener(this);
		
		txtEmail = (EditText) findViewById(R.id.txtEmailRecuperacao);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btCriarConta){
			startActivity(new Intent(this, CadastroActivity.class));
			
		}else if(v.getId() == R.id.textViewEsqueciSenha){
			startActivity(new Intent(LoginActivity.this,RecuperarSenhaActivity.class));
			
		}else if(v.getId() == R.id.btConectar){
			logar();
		}
	}
	
	private void logar(){
		
		String email = txtEmail.getText().toString();
		String senha = txtSenha.getText().toString();
		
		startActivity(new Intent(LoginActivity.this,CategoriasActivity.class));
	}
}
