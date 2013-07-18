package com.master.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.master.android.R;
import com.master.android.dominio.Categoria;

public class CategoriaAdapter extends BaseAdapter{

	private List<Categoria> lista;
	private Context context; 
	
	public CategoriaAdapter(Context context, List<Categoria> lista){
		this.context = context;
		this.lista = lista;
	}
	
	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		Categoria categoria = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.layout_item_lista_categoria, parent, false);
		view = carregarValores(view, categoria);
		
		return view;
	}
	
	private View carregarValores(View view, Categoria categoria){
		
		TextView txtNome = (TextView) view.findViewById(R.id.nome_categoria);
		ImageView imagem = (ImageView) view.findViewById(R.id.icone_categoria);
		
		txtNome.setText(categoria.getNome());
		imagem.setImageResource(categoria.getIcone());
		
		return view;
	}
}
