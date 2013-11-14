package com.master.android.activity;

import java.util.List;

import com.master.android.R;
import com.master.android.R.layout;
import com.master.android.R.menu;
import com.master.android.adapter.CategoriaAdapter;
import com.master.android.adapter.EstabelecimentoAdapter;
import com.master.android.dominio.Categoria;
import com.master.android.dominio.Estabelecimento;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

public class EstabelecimentosActivity extends ListActivity {
	
	private List<Estabelecimento> lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		montarLista();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.estabelecimentos, menu);
		return true;
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		//Categoria evento = (Categoria) this.getListAdapter().getItem(position);
		Intent intent = new Intent(getApplicationContext(), ChamarSenhaActivity.class);
		startActivity(intent);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			montarLista();
		}
	}
	
	private void montarLista(){
		
		lista = Estabelecimento.getEstabelecimentos();
		EstabelecimentoAdapter adapter = new EstabelecimentoAdapter(this, lista);
		setListAdapter(adapter);
		
	}
	
}
