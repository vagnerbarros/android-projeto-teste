package com.master.android.activity;

import java.util.List;

import com.master.android.R;
import com.master.android.adapter.EstabelecimentoAdapter;
import com.master.android.dominio.Estabelecimento;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class EstabelecimentosActivity extends Activity implements
		OnItemClickListener {

	private List<Estabelecimento> lista;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_estabelecimentos);

		listView = (ListView) findViewById(R.id.listaEstabelecimentos);

		montarLista();

	}

	private void montarLista() {

		lista = Estabelecimento.getEstabelecimentos();
		EstabelecimentoAdapter adapter = new EstabelecimentoAdapter(this, lista);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		
		startActivity(new Intent(getApplicationContext(),
				ChamarSenhaActivity.class));
	
	}

}
