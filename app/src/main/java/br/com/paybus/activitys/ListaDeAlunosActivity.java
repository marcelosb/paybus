package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.paybus.R;

public class ListaDeAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_alunos);
    }

    public void irParaCadastrarAluno(View view){
        startActivity(new Intent(ListaDeAlunosActivity.this, CadastrarAlunoActivity.class));
    }



}
