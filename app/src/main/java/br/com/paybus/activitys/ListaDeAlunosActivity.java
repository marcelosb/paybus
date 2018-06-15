package br.com.paybus.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.utilitarios.AlunoRecyclerViewAdapter;

public class ListaDeAlunosActivity extends AppCompatActivity{

    public List<Aluno> listaAlunos;
    private AlunoRecyclerViewAdapter alunoRecyclerViewAdapter;
    private RecyclerView listaDeAlunosrecyclerView;
    public AlunoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_alunos);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.campo_pesquisar_na_action_bar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#006666")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarRecyclerViewAluno();

        EditText campoBuscarAluno = findViewById(R.id.campoBuscar);
        campoBuscarAluno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filtro(s.toString());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ListaDeAlunosActivity.this.finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filtro(String text){
        ArrayList<Aluno> novaLista = new ArrayList<>();
        for(Aluno aluno: listaAlunos){
            if(aluno.getNomeCompleto().toLowerCase().contains(text.toLowerCase())){
                novaLista.add(aluno);
            }
        }
        alunoRecyclerViewAdapter.setFiltro(novaLista);
    }


    private void criarRecyclerViewAluno() {
        listaAlunos = new ArrayList<Aluno>();
        dao = new AlunoDAO(this);
        listaAlunos = dao.listarAlunos();
        listaDeAlunosrecyclerView = findViewById(R.id.alunoRecyclerView);
        listaDeAlunosrecyclerView.setHasFixedSize(true);
        listaDeAlunosrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        alunoRecyclerViewAdapter = new AlunoRecyclerViewAdapter(listaAlunos, this);
        listaDeAlunosrecyclerView.setAdapter(alunoRecyclerViewAdapter);
        //listaDeAlunosrecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void irParaCadastrarAluno(View view){
        ListaDeAlunosActivity.this.finish();
        startActivity(new Intent(ListaDeAlunosActivity.this, CadastrarAlunoActivity.class));
    }

}
