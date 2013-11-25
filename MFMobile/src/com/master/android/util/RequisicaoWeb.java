package com.master.android.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class RequisicaoWeb extends AsyncTask<String, Void, Boolean>  {
	
	private ProgressDialog dialog;
	private Context context;
	private Intent proximaActivity;

	public RequisicaoWeb(Context context, Intent proximaActivity) {
		this.context = context;
		this.proximaActivity = proximaActivity;
	}
	
	public RequisicaoWeb(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(context);
		dialog.setMessage("Aguarde...");
		dialog.show();
	}

	
	@Override
	protected Boolean doInBackground(String...link) {

		Boolean retorno = false;
		try{

			URL url = new URL(link[0]);
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
			((Activity)context).setResult(Activity.RESULT_OK);
			if(proximaActivity != null){
				context.startActivity(proximaActivity);
			}
			else{
				((Activity)context).finish();
			}
		}
		else{
			((Activity)context).setResult(Activity.RESULT_CANCELED);
			((Activity)context).finish();
		}
	}
}
