package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.paybus.R;

public class CadastrarUsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar_usuarios);
    }

    public void irParaCadastroDeMotorista(View view){
        startActivity(new Intent(CadastrarUsuariosActivity.this, CadastrarMotoristaActivity.class));
    }

    public void irParaCadastroDeCobrador(View view){
        startActivity(new Intent(CadastrarUsuariosActivity.this, CadastrarAlunoActivity.class));
    }

    public void irParaCadastroDeAluno(View view){
        startActivity(new Intent(CadastrarUsuariosActivity.this, CadastrarAlunoActivity.class));
    }

}
