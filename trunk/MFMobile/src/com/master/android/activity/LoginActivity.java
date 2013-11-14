package com.master.android.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
import android.widget.TextView;
import android.widget.Toast;

import com.master.android.R;
import com.master.android.util.Criptografia;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginActivity extends Activity implements OnClickListener {

	private final int RECUPERAR = 1;
	private final int CADASTRAR = 2; 
	
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
			Intent i = new Intent(this, CadastroActivity.class);
			startActivityForResult(i, CADASTRAR);

		}else if(v.getId() == R.id.textViewEsqueciSenha){
			Intent i = new Intent(LoginActivity.this,RecuperarSenhaActivity.class);
			startActivityForResult(i, RECUPERAR);

		}else if(v.getId() == R.id.btConectar){
			logar();
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode == RESULT_OK){
			if(requestCode == RECUPERAR){
				Toast.makeText(this, "Uma nova senha foi enviada para seu e-mail", Toast.LENGTH_LONG).show();
			}
			else if(requestCode == CADASTRAR){
				Toast.makeText(this, "Agora você já pode logar", Toast.LENGTH_LONG).show();
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	private void logar() {		

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);


		String email = txtEmail.getText().toString();
		String senha = txtSenha.getText().toString();

		String consulta = "login=" + email + "&senha=" + Criptografia.encryptPassword(senha); 
		String link = "http://10.0.2.2:8080/MasterFila/controlador?acao=logar_android&" + consulta;

		try{

			URL url = new URL(link);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			conexao.setDoOutput(false);
			conexao.setDoInput(true);
			conexao.setRequestMethod("GET");

			if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
				startActivity(new Intent(LoginActivity.this,CategoriasActivity.class));
			}
			else{
				Toast.makeText(this, "Email/Senha Incorretos.", Toast.LENGTH_SHORT).show();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

