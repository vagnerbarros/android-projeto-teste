package com.master.android.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.master.android.R;
import com.master.android.adapter.CategoriaAdapter;
import com.master.android.dominio.Categoria;

public class CategoriasActivity extends Activity implements OnItemClickListener {

	private List<Categoria> lista;
	private ListView listView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_categorias);

		listView = (ListView) findViewById(R.id.listaCategoriasPlinio);

		montarLista();
	}

	private void montarLista() {

		lista = Categoria.getCategorias();
		CategoriaAdapter adapter = new CategoriaAdapter(this, lista);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

		startActivity(new Intent(getApplicationContext(),EstabelecimentosActivity.class));

	}

}
