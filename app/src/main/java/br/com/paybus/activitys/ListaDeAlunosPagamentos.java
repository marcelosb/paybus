package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.utilitarios.PagamentoAlunoRecyclerViewAdapter;
import br.com.paybus.utilitarios.PagamentoRecyclerViewAdapter;

public class ListaDeAlunosPagamentos extends AppCompatActivity {

    public static int numeroDeDevedores;
    public static int numeroDePagadores;
    public static int totalPagadoresEDevedores;

    public List<Pagamento> listaPagamentosAlunos;
    private PagamentoAlunoRecyclerViewAdapter pagamentoAlunoRecyclerViewAdapter;
    private RecyclerView listaDePagamentoAlunoRecyclerView;
    public PagamentoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_alunos_pagamentos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView txt = findViewById(R.id.txtMesPagamento);
        txt.setText(PagamentoRecyclerViewAdapter.mesPagamento);

        criarRecyclerViewPagamentoAluno();

        EditText buscarAlunoPagamento = findViewById(R.id.campoBuscaPagamentoAluno);
        buscarAlunoPagamento.addTextChangedListener(new TextWatcher() {
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

        new PagamentoDAO(this).listarPagamentosPagadoresEDevedores(PagamentoRecyclerViewAdapter.mesPagamento, "Pago");
        new PagamentoDAO(this).listarPagamentosPagadoresEDevedores(PagamentoRecyclerViewAdapter.mesPagamento, "Não Pago");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pagamentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ListaDeAlunosPagamentos.this.finish();
                startActivity(new Intent(ListaDeAlunosPagamentos.this, ListarPagamentos.class));
                return true;

            case R.id.menu_devedores:
                ListaDeAlunosPagamentos.numeroDeDevedores = 0;
                listaPagamentosAlunos = new ArrayList<Pagamento>();
                dao = new PagamentoDAO(ListaDeAlunosPagamentos.this);
                listaPagamentosAlunos = dao.listarPagamentosPagadoresEDevedores(PagamentoRecyclerViewAdapter.mesPagamento, "Não Pago");
                listaDePagamentoAlunoRecyclerView = findViewById(R.id.listaDeAlunosPagamentosRecyclerView);
                listaDePagamentoAlunoRecyclerView.setHasFixedSize(true);
                listaDePagamentoAlunoRecyclerView.setLayoutManager(new LinearLayoutManager(ListaDeAlunosPagamentos.this));
                pagamentoAlunoRecyclerViewAdapter = new PagamentoAlunoRecyclerViewAdapter(listaPagamentosAlunos, ListaDeAlunosPagamentos.this);
                listaDePagamentoAlunoRecyclerView.setAdapter(pagamentoAlunoRecyclerViewAdapter);

                Toast.makeText(this,String.valueOf(ListaDeAlunosPagamentos.numeroDeDevedores) +" alunos não efetuaram o pagamento do ônibus em "+PagamentoRecyclerViewAdapter.mesPagamento, Toast.LENGTH_LONG ).show();

                break;

            case R.id.menu_pagadores:

                ListaDeAlunosPagamentos.numeroDePagadores = 0;
                listaPagamentosAlunos = new ArrayList<Pagamento>();
                dao = new PagamentoDAO(this);
                listaPagamentosAlunos = dao.listarPagamentosPagadoresEDevedores(PagamentoRecyclerViewAdapter.mesPagamento, "Pago");
                listaDePagamentoAlunoRecyclerView = findViewById(R.id.listaDeAlunosPagamentosRecyclerView);
                listaDePagamentoAlunoRecyclerView.setHasFixedSize(true);
                listaDePagamentoAlunoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                pagamentoAlunoRecyclerViewAdapter = new PagamentoAlunoRecyclerViewAdapter(listaPagamentosAlunos, this);
                listaDePagamentoAlunoRecyclerView.setAdapter(pagamentoAlunoRecyclerViewAdapter);

                Toast.makeText(this,String.valueOf(ListaDeAlunosPagamentos.numeroDePagadores) +" alunos realizaram o pagamento do ônibus em "+PagamentoRecyclerViewAdapter.mesPagamento, Toast.LENGTH_LONG ).show();

                break;
            case R.id.menu_estatistica:

                ListaDeAlunosPagamentos.this.finish();
                startActivity(new Intent(ListaDeAlunosPagamentos.this, EstatisticaActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void criarRecyclerViewPagamentoAluno() {
        listaPagamentosAlunos = new ArrayList<Pagamento>();
        dao = new PagamentoDAO(this);
        listaPagamentosAlunos = dao.listarPagamentosDosAlunos(PagamentoRecyclerViewAdapter.mesPagamento);
        //listaPagamentosAlunos = dao.listarPagamentosDosAlunos();
        listaDePagamentoAlunoRecyclerView = findViewById(R.id.listaDeAlunosPagamentosRecyclerView);
        listaDePagamentoAlunoRecyclerView.setHasFixedSize(true);
        listaDePagamentoAlunoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pagamentoAlunoRecyclerViewAdapter = new PagamentoAlunoRecyclerViewAdapter(listaPagamentosAlunos, this);
        listaDePagamentoAlunoRecyclerView.setAdapter(pagamentoAlunoRecyclerViewAdapter);
    }

    private void filtro(String text){
        ArrayList<Pagamento> novaListaPagamento = new ArrayList<>();
        for(Pagamento pagamento: listaPagamentosAlunos){
            if(pagamento.getNomeDoAluno().toLowerCase().contains(text.toLowerCase())){
                novaListaPagamento.add(pagamento);
            }
        }
        pagamentoAlunoRecyclerViewAdapter = new PagamentoAlunoRecyclerViewAdapter(novaListaPagamento, this);
        listaDePagamentoAlunoRecyclerView.setAdapter(pagamentoAlunoRecyclerViewAdapter);
        //pagamentoAlunoRecyclerViewAdapter.setFiltro(novaListaPagamento);
    }

    public static String mesDePagamento;
    public static String dataDeVencimento;

    public void botaoCadastrarNovoAlunoPagamento(View view){
        TelaPrincipalActivity.novoAlunoPagamento = "verdadeiro";
        mesDePagamento = PagamentoRecyclerViewAdapter.mesPagamento;
        dataDeVencimento = PagamentoRecyclerViewAdapter.dataVencimento;
        ListaDeAlunosPagamentos.this.finish();
        startActivity(new Intent(ListaDeAlunosPagamentos.this, CadastrarAlunoActivity.class));
    }

    public void botaoMostrarPagadores(View view){
        ListaDeAlunosPagamentos.numeroDePagadores = 0;
        listaPagamentosAlunos = new ArrayList<Pagamento>();
        dao = new PagamentoDAO(this);
        listaPagamentosAlunos = dao.listarPagamentosPagadoresEDevedores(PagamentoRecyclerViewAdapter.mesPagamento, "Pago");
        listaDePagamentoAlunoRecyclerView = findViewById(R.id.listaDeAlunosPagamentosRecyclerView);
        listaDePagamentoAlunoRecyclerView.setHasFixedSize(true);
        listaDePagamentoAlunoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pagamentoAlunoRecyclerViewAdapter = new PagamentoAlunoRecyclerViewAdapter(listaPagamentosAlunos, this);
        listaDePagamentoAlunoRecyclerView.setAdapter(pagamentoAlunoRecyclerViewAdapter);

        Toast.makeText(this,String.valueOf(ListaDeAlunosPagamentos.numeroDePagadores) +" alunos realizaram o pagamento do ônibus em "+PagamentoRecyclerViewAdapter.mesPagamento, Toast.LENGTH_LONG ).show();
    }

    public void botaoMostrarDevedores(View view){
        ListaDeAlunosPagamentos.numeroDeDevedores = 0;
        listaPagamentosAlunos = new ArrayList<Pagamento>();
        dao = new PagamentoDAO(ListaDeAlunosPagamentos.this);
        listaPagamentosAlunos = dao.listarPagamentosPagadoresEDevedores(PagamentoRecyclerViewAdapter.mesPagamento, "Não Pago");
        listaDePagamentoAlunoRecyclerView = findViewById(R.id.listaDeAlunosPagamentosRecyclerView);
        listaDePagamentoAlunoRecyclerView.setHasFixedSize(true);
        listaDePagamentoAlunoRecyclerView.setLayoutManager(new LinearLayoutManager(ListaDeAlunosPagamentos.this));
        pagamentoAlunoRecyclerViewAdapter = new PagamentoAlunoRecyclerViewAdapter(listaPagamentosAlunos, ListaDeAlunosPagamentos.this);
        listaDePagamentoAlunoRecyclerView.setAdapter(pagamentoAlunoRecyclerViewAdapter);

        Toast.makeText(this,String.valueOf(ListaDeAlunosPagamentos.numeroDeDevedores) +" alunos não efetuaram o pagamento do ônibus em "+PagamentoRecyclerViewAdapter.mesPagamento, Toast.LENGTH_LONG ).show();
    }

    public void botaoMostrarTodos(View view){
        criarRecyclerViewPagamentoAluno();
        Toast.makeText(this,String.valueOf(ListaDeAlunosPagamentos.totalPagadoresEDevedores) +" alunos estão na lista de pagamento do mês de "+PagamentoRecyclerViewAdapter.mesPagamento, Toast.LENGTH_LONG ).show();
    }

    public void botaoMostrarEstatisticas(View view){
        ListaDeAlunosPagamentos.this.finish();
        startActivity(new Intent(ListaDeAlunosPagamentos.this, EstatisticaActivity.class));
    }

}
