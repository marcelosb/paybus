package br.com.paybus.activitys;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.paybus.R;
import br.com.paybus.utilitarios.AlunoRecyclerViewAdapter;

public class ListaDeAlunosActivity extends AppCompatActivity {

    private AlunoRecyclerViewAdapter alunoRecyclerViewAdapter;
    private RecyclerView listaDeAlunosrecyclerView;
    private FloatingActionButton fab;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_alunos);

        fab = findViewById(R.id.fab);
        listaDeAlunosrecyclerView = findViewById(R.id.alunoRecyclerView);

        alunoRecyclerViewAdapter = new AlunoRecyclerViewAdapter();

        listaDeAlunosrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaDeAlunosrecyclerView.setAdapter(alunoRecyclerViewAdapter);
    }

    public void irParaCadastrarAluno(View view){
        startActivity(new Intent(ListaDeAlunosActivity.this, CadastrarAlunoActivity.class));
    }



}
