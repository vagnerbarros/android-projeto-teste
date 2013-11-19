package com.master.android.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

public class RecuperarSenhaActivity extends Activity implements OnClickListener {
	
	private EditText txtEmail;
	private Button btRecuperarSenha;
	private Button btVoltarLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_recuperar_senha);
	
		txtEmail = (EditText) findViewById(R.id.txtEmailRecuperacao);
		
		btRecuperarSenha = (Button) findViewById(R.id.btRecuperar);
		btRecuperarSenha.setOnClickListener(this);
		
		btVoltarLogin = (Button) findViewById(R.id.btVoltarLogin);
		btVoltarLogin.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recuperar_senha, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btRecuperar){
			if(!txtEmail.getText().toString().equals("")){
				setResult(RESULT_OK);
				enviarSolicitacao(txtEmail.getText().toString());
				finish();
			}
			else{
				Toast.makeText(this, "Preencha o e-mail.", Toast.LENGTH_SHORT).show();
			}
		}else if(v.equals(btVoltarLogin)){
			finish();
		}
	}

	private void enviarSolicitacao(String email){
		OperacaoRecuperarSenha op = new OperacaoRecuperarSenha(this, email);
		op.execute();
	}
	
	class OperacaoRecuperarSenha extends AsyncTask<Void, Void, Void>  {

		private ProgressDialog dialog;
		private Context context;
		private String email;

		public OperacaoRecuperarSenha(Context context, String e) {
			this.context = context;
			this.email = e;
		}

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setMessage("Aguarde...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			String consulta = "email=" + email; 
			String link = "http://10.0.2.2:8080/MasterFila/controlador?acao=recuperar_senha&" + consulta;
			try{

				URL url = new URL(link);
				HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

				conexao.setDoOutput(false);
				conexao.setDoInput(true);
				conexao.setRequestMethod("GET");
				
				conexao.getResponseCode();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			dialog.setMessage("Aguarde ...");
		}

		@Override
		protected void onPostExecute(Void result) {
			dialog.dismiss();
		}
	}
}
