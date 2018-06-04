package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.MesDoPagamentoDAO;
import br.com.paybus.modelo.MesDoPagamento;
import br.com.paybus.utilitarios.PagamentoRecyclerViewAdapter;

public class ListarPagamentos extends AppCompatActivity {

    public List<MesDoPagamento> listaPagamentosMes;
    private PagamentoRecyclerViewAdapter pagamentoRecyclerViewAdapter;
    private RecyclerView listaDePagamentoPorMesRecyclerView;
    public MesDoPagamentoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_meses_de_pagamentos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarRecyclerViewPagamentoPorMes();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void criarRecyclerViewPagamentoPorMes() {
        listaPagamentosMes = new ArrayList<MesDoPagamento>();
        dao = new MesDoPagamentoDAO(this);

        listaPagamentosMes = dao.listarMesesDoPagamento();
        listaDePagamentoPorMesRecyclerView = findViewById(R.id.listaDePagamentosDoMesRecyclerView);
        listaDePagamentoPorMesRecyclerView.setHasFixedSize(true);
        listaDePagamentoPorMesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pagamentoRecyclerViewAdapter = new PagamentoRecyclerViewAdapter(listaPagamentosMes, this);
        listaDePagamentoPorMesRecyclerView.setAdapter(pagamentoRecyclerViewAdapter);
    }

    public void botaoCriarNovoMesDePagamento(View view){
        ListarPagamentos.this.finish();
        startActivity(new Intent(ListarPagamentos.this, CadastrarNovoMesDePagamento.class ));
    }


}
