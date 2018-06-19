package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.MesDoPagamentoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.MesDoPagamento;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.utilitarios.PagamentoRecyclerViewAdapter;
import br.com.paybus.utilitarios.Verifica;

public class EditarNovoMesDePagamentoActivity extends AppCompatActivity {

    AlunoDAO alunoDAO;
    List<Aluno> listaDeAlunos;
    MesDoPagamento mesDoPagamento;
    MesDoPagamentoDAO mesDoPagamentoDAO;

    Pagamento pagamento;
    PagamentoDAO pagamentoDAO;

    Verifica verifica;

    List<Pagamento> listPags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_novo_mes_de_pagamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PagamentoDAO dao = new PagamentoDAO(this);
        listPags =  dao.listarPagamentosDosAlunosPorMes(PagamentoRecyclerViewAdapter.mesPagamento);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(EditarNovoMesDePagamentoActivity.this, ListarPagamentos.class));
                EditarNovoMesDePagamentoActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditarNovoMesDePagamentoActivity.this, ListarPagamentos.class));
        EditarNovoMesDePagamentoActivity.this.finish();
    }

    public void botaoEditarNovoMesDePagamento(View view){

        mesDoPagamento = new MesDoPagamento();
        mesDoPagamentoDAO = new MesDoPagamentoDAO(this);

        int id = getIntent().getIntExtra("id_mes_pagamento", -1) ;
        mesDoPagamento.setId(id);
        Spinner comboBoxSelecionarMesDePagamentos = findViewById(R.id.campoSelecioneMesDePagamentosEditar);
        String mesDePagamentos = comboBoxSelecionarMesDePagamentos.getSelectedItem().toString();
        Spinner comboBoxSelecionarAnoDePagamentos = findViewById(R.id.campoSelecioneAnoDePagamentosEditar);
        String anoDePagamentos = comboBoxSelecionarAnoDePagamentos.getSelectedItem().toString();
        mesDoPagamento.setMesEAnoDoPagamento(mesDePagamentos+" de "+anoDePagamentos);
        Spinner comboBoxSelecionarDiaDePagamentoVencimento = findViewById(R.id.selecioneDiaDoPagamentoVencimentoEditar);
        String diaDePagamentoVencimento = comboBoxSelecionarDiaDePagamentoVencimento.getSelectedItem().toString();
        Spinner comboBoxSelecionarMesDePagamentoVencimento = findViewById(R.id.selecioneMesDoPagamentoVencimentoEditar);
        String mesDePagamentoVencimento = comboBoxSelecionarMesDePagamentoVencimento.getSelectedItem().toString();
        if(mesDePagamentoVencimento.equals("Janeiro")){
            mesDePagamentoVencimento = "01";
        }else if(mesDePagamentoVencimento.equals("Fevereiro")){
            mesDePagamentoVencimento = "02";
        }else if(mesDePagamentoVencimento.equals("Março")){
            mesDePagamentoVencimento = "03";
        }else if(mesDePagamentoVencimento.equals("Abril")){
            mesDePagamentoVencimento = "04";
        }else if(mesDePagamentoVencimento.equals("Maio")){
            mesDePagamentoVencimento = "05";
        }else if(mesDePagamentoVencimento.equals("Junho")){
            mesDePagamentoVencimento = "06";
        }else if(mesDePagamentoVencimento.equals("Julho")){
            mesDePagamentoVencimento = "07";
        }else if(mesDePagamentoVencimento.equals("Agosto")){
            mesDePagamentoVencimento = "08";
        }else if(mesDePagamentoVencimento.equals("Setembro")){
            mesDePagamentoVencimento = "09";
        }else if(mesDePagamentoVencimento.equals("Outubro")){
            mesDePagamentoVencimento = "10";
        }else if(mesDePagamentoVencimento.equals("Novembro")){
            mesDePagamentoVencimento = "11";
        }else if(mesDePagamentoVencimento.equals("Dezembro")){
            mesDePagamentoVencimento = "12";
        }
        Spinner comboBoxSelecionarAnoDePagamentoVencimento = findViewById(R.id.selecioneAnoDoPagamentoVencimentoEditar);
        String anoDePagamentoVencimento = comboBoxSelecionarAnoDePagamentoVencimento.getSelectedItem().toString();
        mesDoPagamento.setDataDoVencimento(diaDePagamentoVencimento+"/"+mesDePagamentoVencimento+"/"+anoDePagamentoVencimento);
        mesDoPagamentoDAO.atualizarMesDoPagamento(mesDoPagamento);

        pagamentoDAO = new PagamentoDAO(this);

        for(int i=0; i<listPags.size(); i++){
            listPags.get(i).setMesEAnoDoPagamento(mesDoPagamento.getMesEAnoDoPagamento());
            listPags.get(i).setDataDoVencimento(mesDoPagamento.getDataDoVencimento());
        }

        pagamentoDAO.atualizarPagamentoMes(listPags);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(EditarNovoMesDePagamentoActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Mês de pagamento alterado com sucesso");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(EditarNovoMesDePagamentoActivity.this, ListarPagamentos.class));
                EditarNovoMesDePagamentoActivity.this.finish();
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }

}
