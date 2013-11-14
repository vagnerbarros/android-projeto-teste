package com.master.android.activity;

import com.master.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChamarSenhaActivity extends Activity implements OnClickListener {

	private Button btVoltarEstabelecimento;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chamar_senha);
		
		btVoltarEstabelecimento = (Button) findViewById(R.id.btVoltarEstabelecimento);
		btVoltarEstabelecimento.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chamar_senha, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btVoltarEstabelecimento){
			finish();
		}
	}

}
