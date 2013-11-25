package com.master.android.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.master.android.R;
import com.master.android.entidade.Ficha;
import com.master.android.entidade.Usuario;
import com.master.android.util.Constantes;
import com.master.android.util.Sessao;

public class ChamarSenhaActivity extends Activity implements OnClickListener {

	private TextView txtEstabelecimento;
	private TextView lblEstimativa;
	private TextView txtEstimativa;
	private TextView lblSenha;
	private TextView txtSenha;
	private ListView listaHistorico;
	private Button btnSolicitar;
	private Button btnAtualizar;
	private List<Ficha> historico;
	private List<Ficha> fila;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chamar_senha);
		
		listaHistorico = (ListView) findViewById(R.id.list_historico);

		txtEstabelecimento = (TextView) findViewById(R.id.txt_estabelecimento);
		txtEstabelecimento.setText(Sessao.escolhido().getNome());
		
		txtEstimativa = (TextView) findViewById(R.id.txt_estimativa);
		txtEstimativa.setVisibility(View.INVISIBLE);
		
		lblEstimativa = (TextView) findViewById(R.id.lbl_estima);
		lblEstimativa.setVisibility(View.INVISIBLE);
		
		txtSenha = (TextView) findViewById(R.id.txt_sua_senha);
		txtSenha.setVisibility(View.INVISIBLE);
		
		lblSenha = (TextView) findViewById(R.id.lbl_sua_senha);
		lblSenha.setVisibility(View.INVISIBLE);

		btnSolicitar = (Button) findViewById(R.id.btSolicitarSenha);
		btnSolicitar.setOnClickListener(this);
		
		btnAtualizar = (Button) findViewById(R.id.btn_atualizar);
		btnAtualizar.setOnClickListener(this);
		
		historico = new ArrayList<Ficha>();
		fila = new ArrayList<Ficha>();
		
		consultarServidor();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chamar_senha, menu);
		return true;
	}
	
	private void consultarServidor(){
		OperacoesSenha op = new OperacoesSenha(this);
		op.execute();
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_atualizar){
			consultarServidor();
		}
		else if(v.getId() == R.id.btSolicitarSenha){
			OperacaoSolicitar op = new OperacaoSolicitar(this);
			op.execute();
		}
	}

	private String acessaWebService(String link) {

		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(false);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return readReponseAsString(connection.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String readReponseAsString(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		reader.close();
		in.close();
		return response.toString();
	}

	private void getFichas() {
		
		String link = Constantes.SERVIDOR + "acao=historico_android&estabelecimento=" + Sessao.escolhido().getId();
		
		String jsonString = acessaWebService(link);
		if(historico.size() > 0){
			historico.clear();
		}
		if(fila.size() > 0){
			fila.clear();
		}
		try {
			JSONObject resultados = new JSONObject(jsonString);
			JSONArray arrayHistorico = resultados.getJSONArray("historico");
			for (int i = 0; i < arrayHistorico.length(); i++) {
				historico.add(montar(arrayHistorico.getJSONObject(i)));
			}
			
			JSONArray arrayFichas = resultados.getJSONArray("fichas");
			for (int i = 0; i < arrayFichas.length(); i++) {
				fila.add(montar(arrayFichas.getJSONObject(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private Ficha montar(JSONObject fichasJSON){
		
		//recuperar tambem o objeto usuario
		String numero;
		try {
			numero = fichasJSON.getString("numero");
			String hora = fichasJSON.getString("hora");
			String login = fichasJSON.getString("login");
			Ficha ficha = new Ficha();
			ficha.setNumero(numero);
			ficha.setHora(hora);
			
			Usuario u = new Usuario();
			u.setLogin(login);
			ficha.setCliente(u);
			
			return ficha;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	class OperacoesSenha extends AsyncTask<Void, String, Void>  {
		private ProgressDialog dialog;
		private Context context;

		public OperacoesSenha(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setMessage("Aguarde...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			getFichas();
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			dialog.setMessage(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			dialog.dismiss();
			ArrayAdapter<Ficha> adapter = new ArrayAdapter<Ficha>(context, android.R.layout.simple_list_item_1, historico);
			listaHistorico.setAdapter(adapter);
			
			int tempoMedia = 3; //minutos
			
			Usuario logado = Sessao.logado();
			if(logado != null){
				int posicao = 0;
				for (int i = 0; i < fila.size(); i++){
					if(fila.get(i).getCliente().getLogin().equals(logado.getLogin())){
						posicao = i + 1;
						lblSenha.setVisibility(View.VISIBLE);
						txtSenha.setVisibility(View.VISIBLE);
						txtSenha.setText(fila.get(i).getNumero());
						
						int estimativa = tempoMedia * (posicao - 1);
						txtEstimativa.setVisibility(View.VISIBLE);
						txtEstimativa.setText(estimativa + " Minutos.");
					}
				}
				if(posicao == 0){
					btnSolicitar.setVisibility(View.VISIBLE);
				}
			}
			else{
				txtEstimativa.setVisibility(View.INVISIBLE);
				lblEstimativa.setVisibility(View.INVISIBLE);
				
				txtSenha.setVisibility(View.INVISIBLE);
				lblSenha.setVisibility(View.INVISIBLE);
				
				btnSolicitar.setVisibility(View.INVISIBLE);
			}
		}
	}
	
	
	private String solicitarSenha() {
		
		String link = Constantes.SERVIDOR + "acao=solicitar_senha&login=" + Sessao.logado().getLogin();
		
		String jsonString = acessaWebService(link);
		try {
			JSONObject resultado = new JSONObject(jsonString);
			String numero = resultado.getString("numero");
			return numero;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	class OperacaoSolicitar extends AsyncTask<Void, String, String>  {
		private ProgressDialog dialog;
		private Context context;

		public OperacaoSolicitar(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setMessage("Aguarde...");
			dialog.show();
		}

		@Override
		protected String doInBackground(Void... params) {
			return solicitarSenha();
		}

		@Override
		protected void onProgressUpdate(String... values) {
			dialog.setMessage(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			
			lblSenha.setVisibility(View.VISIBLE);
			txtSenha.setVisibility(View.VISIBLE);
			txtSenha.setText(result);
			
			consultarServidor();
		}
	}
}
