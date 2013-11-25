package com.master.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.master.android.R;
import com.master.android.util.Constantes;
import com.master.android.util.RequisicaoWeb;

public class RecuperarSenhaActivity extends Activity implements OnClickListener {
	
	private EditText txtEmail;
	private Button btRecuperarSenha;
	private Button btVoltarLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_recuperar_senha);
	
		txtEmail = (EditText) findViewById(R.id.txtEmailRecuperacao);
		
		btRecuperarSenha = (Button) findViewById(R.id.btRecuperar);
		btRecuperarSenha.setOnClickListener(this);
		
		btVoltarLogin = (Button) findViewById(R.id.btVoltarLogin);
		btVoltarLogin.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recuperar_senha, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btRecuperar){
			if(!txtEmail.getText().toString().equals("")){
				recuperar();
			}
			else{
				Toast.makeText(this, "Preencha o e-mail.", Toast.LENGTH_SHORT).show();
			}
		}else if(v.equals(btVoltarLogin)){
			finish();
		}
	}
	
	private void recuperar(){
		
		String consulta = "email=" + txtEmail.getText().toString(); 
		String link = Constantes.SERVIDOR + "acao=recuperar_senha&" + consulta;
		
		RequisicaoWeb req = new RequisicaoWeb(this);
		req.execute(link);
	}
}
