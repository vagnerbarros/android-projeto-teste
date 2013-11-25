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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.master.android.R;
import com.master.android.adapter.EstabelecimentoAdapter;
import com.master.android.dominio.Categoria;
import com.master.android.entidade.Estabelecimento;
import com.master.android.util.Constantes;
import com.master.android.util.Sessao;

public class EstabelecimentosActivity extends Activity implements OnItemClickListener, OnClickListener, android.content.DialogInterface.OnClickListener {

	private ListView listView;
	private TextView txtCidade;
	private TextView txtCategoria;
	private String categoriaAtual = "";
	private String cidadeAtual = "";
	private TextView txtCidadeAtual;
	private TextView txtCategoriaAtual;
	private AlertDialog.Builder dialogCategoria;
	private AlertDialog.Builder dialogCidade;
	private String tipo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_estabelecimentos);

		txtCidade = (TextView) findViewById(R.id.txtCidade);
		txtCategoria = (TextView) findViewById(R.id.txtCategoria);
		txtCidade.setOnClickListener(this);
		txtCategoria.setOnClickListener(this);
		
		txtCidadeAtual = (TextView) findViewById(R.id.textView1);
		txtCategoriaAtual = (TextView) findViewById(R.id.textView2);

		dialogCategoria = new Builder(this);
		dialogCategoria.setTitle("Selecione a Categoria");
		String[] categorias = Categoria.categorias();
		dialogCategoria.setSingleChoiceItems(categorias, 0, this);

		dialogCidade = new Builder(this);
		dialogCidade.setTitle("Selecione a Cidade");
		String[] cidades = {"Caruaru - PE", "Toritama - PE", "Recife - PE", "Santa Cruz - PE"};
		dialogCidade.setSingleChoiceItems(cidades, 0, this);

		listView = (ListView) findViewById(R.id.listaEstabelecimentos);
		listView.setOnItemClickListener(this);
		
		cidadeAtual = "Caruaru - PE";
		categoriaAtual = Categoria.AGENCIA;
		montarLista();
	}

	private void montarLista() {

		OperacaoTask op = new OperacaoTask(this);
		op.execute();
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.txtCategoria){
			tipo = "categoria";
			dialogCategoria.show();
		}
		else if(v.getId() == R.id.txtCidade){
			tipo = "cidade";
			dialogCidade.show();
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();
		if(tipo.equals("categoria")){
			switch(which){
			case 0:
				categoriaAtual = Categoria.CONSULTORIO;
				break;
			case 1:
				categoriaAtual = Categoria.REPARTICAO;
				break;
			case 2:
				categoriaAtual = Categoria.AGENCIA;
				break;
			case 3:
				categoriaAtual = Categoria.CARTORIO;
				break;
			case 4:
				categoriaAtual = Categoria.PREFEITURA;
				break;
			}
			txtCategoriaAtual.setText(categoriaAtual);
		}
		else if(tipo.equals("cidade")){
			switch(which){
			case 0:
				cidadeAtual = "Caruaru - PE";
				break;
			case 1:
				cidadeAtual = "Toritama - PE";
				break;
			case 2:
				cidadeAtual = "Recife - PE";
				break;
			case 3:
				cidadeAtual = "Santa Cruz - PE";
				break;
			}
			txtCidadeAtual.setText(cidadeAtual);
		}
		montarLista();
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Estabelecimento estab = (Estabelecimento) adapter.getItemAtPosition(position);
		Sessao.escolherEstabelecimento(estab);
		startActivity(new Intent(this, ChamarSenhaActivity.class));
	}
	
	private String codigoCategoria(){
		String cat = "";
		if(categoriaAtual.equals(Categoria.CONSULTORIO)){
			cat = "1"; 
		}
		else if(categoriaAtual.equals(Categoria.REPARTICAO)){
			cat = "2";
		}
		else if(categoriaAtual.equals(Categoria.AGENCIA)){
			cat = "3";
		}
		else if(categoriaAtual.equals(Categoria.CARTORIO)){
			cat = "4";
		}
		else if(categoriaAtual.equals(Categoria.PREFEITURA)){
			cat = "5";
		}
		return cat;
	}
	
	private String codigoCidade(){
		String cid = "";
		if(cidadeAtual.equals("Caruaru - PE")){
			cid = "1";
		}else if(cidadeAtual.equals("Toritama - PE")){
			cid = "2";
		}else if(cidadeAtual.equals("Recife - PE")){
			cid = "3";
		}else if(cidadeAtual.equals("Santa Cruz - PE")){
			cid = "4";
		}
		return cid;
	}

	private String acessaWebService() {
		
		String cat = codigoCategoria();
		String cid = codigoCidade();
		String link = Constantes.SERVIDOR + "acao=listar_android";
		if(!cat.equals("") && cid.equals("")){
			link = link + "&categoria=" + cat;
		}
		else if(cat.equals("") && !cid.equals("")){
			link = link + "&cidade=" + cid;
		}
		else if(!cat.equals("") && !cid.equals("")){
			link = link + "&categoria=" + cat + "&cidade=" + cid;
		}

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

	private List<Estabelecimento> getEstabelecimentos() {
		List<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String jsonString = acessaWebService();

		try {
			JSONObject resultados = new JSONObject(jsonString);
			JSONArray recordsArray = resultados.getJSONArray("empresas");
			for (int i = 0; i < recordsArray.length(); i++) {
				JSONObject estabelecimentoJson = recordsArray.getJSONObject(i);
				String id = estabelecimentoJson.getString("id");
				String nome = estabelecimentoJson.getString("nome");
				Estabelecimento estabelecimento = new Estabelecimento();
				estabelecimento.setId(id);
				estabelecimento.setNome(nome);
				estabelecimento.setIcone(Categoria.getIconeCategoria(categoriaAtual));
				estabelecimentos.add(estabelecimento);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return estabelecimentos;
	}

	class OperacaoTask extends AsyncTask<Void, String, List<Estabelecimento>>  {
		private ProgressDialog dialog;
		private Context context;

		public OperacaoTask(Context context) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setMessage("Aguarde...");
			dialog.show();
		}

		@Override
		protected List<Estabelecimento> doInBackground(Void... params) {
			List<Estabelecimento> estabelecimentos = getEstabelecimentos();
			return estabelecimentos;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			dialog.setMessage(values[0]);
		}

		@Override
		protected void onPostExecute(List<Estabelecimento> result) {
			dialog.dismiss();
			ListView listView = (ListView) findViewById(R.id.listaEstabelecimentos);
			EstabelecimentoAdapter adapter = new EstabelecimentoAdapter(context, result);
			listView.setAdapter(adapter);
		}
	}
}
