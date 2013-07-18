package com.master.android.activity;

import com.master.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener {
	
	Button btCriarConta;
	Button btConectar;
	TextView txBtEsqueciSenha;
	
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.equals(btCriarConta)){
			startActivity(new Intent(LoginActivity.this,CadastroActivity.class));
			this.onDestroy();
		}else if(v.equals(txBtEsqueciSenha)){
			startActivity(new Intent(LoginActivity.this,RecuperarSenhaActivity.class));
		}else if(v.equals(btConectar)){
			startActivity(new Intent(LoginActivity.this,CategoriasActivity.class));
		}
	}
}
