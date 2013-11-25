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
import com.master.android.entidade.Estabelecimento;

public class EstabelecimentoAdapter extends BaseAdapter{

	private List<Estabelecimento> lista;
	private Context context; 
	
	public EstabelecimentoAdapter(Context context, List<Estabelecimento> lista){
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
		
		Estabelecimento estabelecimento = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.layout_item_lista_categoria, parent, false);
		view = carregarValores(view, estabelecimento);
		
		return view;
	}
	
	private View carregarValores(View view, Estabelecimento estabelecimento){
		
		TextView txtNome = (TextView) view.findViewById(R.id.nome_categoria);
		ImageView imagem = (ImageView) view.findViewById(R.id.icone_categoria);
		
		txtNome.setText(estabelecimento.getNome());
		imagem.setImageResource(estabelecimento.getIcone());
		
		return view;
	}
}
