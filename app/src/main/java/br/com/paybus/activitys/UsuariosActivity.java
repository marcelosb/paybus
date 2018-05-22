package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.paybus.R;

public class UsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_usuarios);
    }

    public void irParaCadastroDeMotorista(View view){
        startActivity(new Intent(UsuariosActivity.this, ListaDeMotoristasActivity.class));
    }

    public void irParaCadastroDeCobrador(View view){
        startActivity(new Intent(UsuariosActivity.this, ListaDeCobradoresActivity.class));
    }

    public void irParaCadastroDeAluno(View view){
        startActivity(new Intent(UsuariosActivity.this, ListaDeAlunosActivity.class));
    }

}
