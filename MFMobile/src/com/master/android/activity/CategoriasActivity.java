package com.master.android.activity;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.master.android.R;
import com.master.android.adapter.CategoriaAdapter;
import com.master.android.dominio.Categoria;

public class CategoriasActivity extends ListActivity {
	
	private List<Categoria> lista;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		montarLista();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.categorias, menu);
		return true;
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		//Categoria evento = (Categoria) this.getListAdapter().getItem(position);
		Intent intent = new Intent(getApplicationContext(), EstabelecimentosActivity.class);
		startActivity(intent);
		
		Categoria evento = (Categoria) this.getListAdapter().getItem(position);
	//	Intent intent = new Intent(getApplicationContext(), DetalharEventoActivity.class);
	//	startActivity(intent);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			montarLista();
		}
	}
	
	private void montarLista(){
		
		lista = Categoria.getCategorias();
		CategoriaAdapter adapter = new CategoriaAdapter(this, lista);
		setListAdapter(adapter);
	}
}
