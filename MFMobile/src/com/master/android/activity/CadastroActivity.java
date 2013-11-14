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
				usuario.put("email", email);
				usuario.put("senha", senha);
				
				String link = "http://10.0.2.2:8080/MasterFila/controlador?acao=cadastrar&json=" + usuario;
				OperacaoCadastrar op = new OperacaoCadastrar(this, link);
				op.execute();
				finish();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class OperacaoCadastrar extends AsyncTask<Void, Void, Boolean>  {
	
	private ProgressDialog dialog;
	private Context context;
	private String link;

	public OperacaoCadastrar(Context context, String l) {
		this.context = context;
		this.link = l;
	}

	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(context);
		dialog.setMessage("Aguarde...");
		dialog.show();
	}

	@Override
	protected Boolean doInBackground(Void... params) {

		Boolean retorno = false;
		try{

			URL url = new URL(link);
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

			conexao.setDoOutput(false);
			conexao.setDoInput(true);
			conexao.setRequestMethod("GET");

			if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
				retorno = true;
			}
			else{
				retorno = false;
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return retorno;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		dialog.setMessage("Aguarde ...");
	}

	@Override
	protected void onPostExecute(Boolean ok) {
		dialog.dismiss();
		if(ok){
			Toast.makeText(context, "Cadastrado com Sucesso.", Toast.LENGTH_SHORT).show();
			((Activity)context).setResult(Activity.RESULT_OK);
		}
		else{
			Toast.makeText(context, "Erro ao Tentar Cadastrar", Toast.LENGTH_SHORT).show();
		}
	}
}
