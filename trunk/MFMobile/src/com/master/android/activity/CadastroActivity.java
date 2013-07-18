package com.master.android.activity;

import com.master.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class CadastroActivity extends Activity implements OnClickListener {
	
	Button btVoltarLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_cadastro);
		
		btVoltarLogin = (Button) findViewById(R.id.btVoltarLogin);
		btVoltarLogin.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.equals(btVoltarLogin)){
			finish();
		}
	}

}
