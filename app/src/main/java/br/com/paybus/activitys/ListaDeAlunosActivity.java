package br.com.paybus.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

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



    /**
         *
         *
         criarRecyclerView();

         EditText campoBuscarAluno = findViewById(R.id.campoPesquisarAluno);
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
         *
         *
         */




    /**

    private void filtro(String text){
        ArrayList<Aluno> novaLista = new ArrayList<>();
        for(Aluno aluno: listaAlunos){
            if(aluno.getNomeCompleto().toLowerCase().contains(text.toLowerCase())){
                novaLista.add(aluno);
            }
        }
        alunoRecyclerViewAdapter.setFiltro(novaLista);
    }

    private void criarRecyclerView() {
        listaAlunos = new ArrayList<Aluno>();
        dao = new AlunoDAO(this);
        listaAlunos = dao.listarAlunos();
        fab = findViewById(R.id.fab);
        listaDeAlunosrecyclerView = findViewById(R.id.alunoRecyclerView);
        listaDeAlunosrecyclerView.setHasFixedSize(true);
        listaDeAlunosrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaDeAlunosrecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        alunoRecyclerViewAdapter = new AlunoRecyclerViewAdapter(listaAlunos, this);
        listaDeAlunosrecyclerView.setAdapter(alunoRecyclerViewAdapter);
    }

    public void irParaCadastrarAluno(View view){
        startActivity(new Intent(ListaDeAlunosActivity.this, CadastrarAlunoActivity.class));
    }

    private AppCompatActivity activity = ListaDeAlunosActivity.this;
    Context context = ListaDeAlunosActivity.this;
    public List<Aluno> listaAlunos;

    private AlunoRecyclerViewAdapter alunoRecyclerViewAdapter;
    private RecyclerView listaDeAlunosrecyclerView;
    private FloatingActionButton fab;
    private ConstraintLayout constraintLayout;

    AlunoDAO dao;






     AQUI COMEÇA A LIST VIEW
========================================================================

     Aluno aluno;
     List<Aluno> listaDeAlunos;
     AlunoDAO dao;
     ListView listaDeAlunosView;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.tela_lista_de_alunos);

     dao = new AlunoDAO(this);
     listaDeAlunos = dao.listarAlunos();

     int layout = android.R.layout.simple_list_item_1;
     ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, listaDeAlunos);
     listaDeAlunosView = findViewById(R.id.alunoListView);
     listaDeAlunosView.setAdapter(adapter);

     registerForContextMenu(listaDeAlunosView);

     listaDeAlunosView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
     @Override
     public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
     aluno = new Aluno();
     aluno = listaDeAlunos.get(position);
     return false;
     }
     });

     }

     @Override
     public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
     MenuItem menuEditar = menu.add("Editar");
     menuEditar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
     @Override
     public boolean onMenuItemClick(MenuItem item) {

     return false;
     }
     });

     MenuItem menuDeletar = menu.add("Deletar");
     menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
     @Override
     public boolean onMenuItemClick(MenuItem item) {

     android.support.v7.app.AlertDialog.Builder caixaDeDialogo = new android.support.v7.app.AlertDialog.Builder(ListaDeAlunosActivity.this);
     caixaDeDialogo.setCancelable(false);
     caixaDeDialogo.setTitle("Confirmar");
     caixaDeDialogo.setMessage("Deseja realmente excluir?");

     caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
     @Override public void onClick(DialogInterface dialogInterface, int i) {

     }
     });

     caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
     @Override public void onClick(DialogInterface dialogInterface, int i) {
     AlunoDAO alunoDAOMenu = new AlunoDAO(ListaDeAlunosActivity.this);
     alunoDAOMenu.deletarAluno(aluno);
     carregaLisra();
     }
     });

     caixaDeDialogo.create();
     caixaDeDialogo.show();



     return false;
     }
     });

     super.onCreateContextMenu(menu, view, menuInfo);
     }

     @Override
     protected void onResume() {
     super.onResume();
     carregaLisra();
     }

     public void carregaLisra(){
     AlunoDAO alunodao = new AlunoDAO(this);
     List<Aluno> alunos = alunodao.listarAlunos();

     int layout = android.R.layout.simple_list_item_1;
     ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);
     listaDeAlunosView.setAdapter(adapter);
     }









     */



}
