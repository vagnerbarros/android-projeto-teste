package com.master.android.activity;

import com.master.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class RecuperarSenhaActivity extends Activity implements OnClickListener {
	
	Button btRecuperarSenha;
	Button btVoltarLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_recuperar_senha);
	
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
		if(v.equals(btRecuperarSenha)){
			Toast.makeText(this, "Foi enviado um email com sua senha", Toast.LENGTH_LONG).show();
		}else if(v.equals(btVoltarLogin)){
			finish();
		}
	}

}
