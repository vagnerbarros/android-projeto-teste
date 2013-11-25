package com.master.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.master.android.R;
import com.master.android.entidade.Usuario;
import com.master.android.util.Constantes;
import com.master.android.util.Criptografia;
import com.master.android.util.RequisicaoWeb;
import com.master.android.util.Sessao;

public class LoginActivity extends Activity implements OnClickListener {

	private final int RECUPERAR = 1;
	private final int CADASTRAR = 2;

	private EditText txtEmail;
	private EditText txtSenha;
	private Button btCriarConta;
	private Button btConectar;
	private TextView txBtEsqueciSenha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_login);

		txBtEsqueciSenha = (TextView) findViewById(R.id.textViewEsqueciSenha);
		txBtEsqueciSenha.setOnClickListener(this);

		btCriarConta = (Button) findViewById(R.id.btCriarConta);
		btCriarConta.setOnClickListener(this);

		btConectar = (Button) findViewById(R.id.btConectar);
		btConectar.setOnClickListener(this);

		txtEmail = (EditText) findViewById(R.id.txtEmailRecuperacao);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		
		txtEmail.setText("email");
		txtSenha.setText("senha");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btCriarConta) {
			Intent i = new Intent(this, CadastroActivity.class);
			startActivityForResult(i, CADASTRAR);

		} else if (v.getId() == R.id.textViewEsqueciSenha) {
			Intent i = new Intent(LoginActivity.this, RecuperarSenhaActivity.class);
			startActivityForResult(i, RECUPERAR);

		} else if (v.getId() == R.id.btConectar) {
			logar();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == RECUPERAR) {
				Toast.makeText(this, "Uma nova senha foi enviada para seu e-mail", Toast.LENGTH_LONG).show();
			} else if (requestCode == CADASTRAR) {
				Toast.makeText(this, "Agora você já pode logar", Toast.LENGTH_LONG).show();
			}
		}
		else if(resultCode == RESULT_CANCELED){
			if (requestCode == RECUPERAR) {
				Toast.makeText(this, "Erro durante a recuperação de senha.", Toast.LENGTH_LONG).show();
			} else if (requestCode == CADASTRAR) {
				Toast.makeText(this, "Erro ao tentar cadastrar", Toast.LENGTH_LONG).show();
			}
		}
	}

	private void logar() {

		String email = txtEmail.getText().toString();
		String senha = txtSenha.getText().toString();
		
		Usuario u = new Usuario();
		u.setLogin(email);
		u.setSenha(senha);
		Sessao.logar(u);

		String consulta = "login=" + email + "&senha=" + Criptografia.encryptPassword(senha);
		String link = Constantes.SERVIDOR + "acao=logar_android&" + consulta;
		
		RequisicaoWeb req = new RequisicaoWeb(this, new Intent(this, EstabelecimentosActivity.class));
		req.execute(link);
	}

}