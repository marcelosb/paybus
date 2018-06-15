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
import br.com.paybus.utilitarios.PainelDeDialogo;
import br.com.paybus.utilitarios.Verifica;

public class CadastrarNovoMesDePagamento extends AppCompatActivity {

    //MesDoPagamentoControl mesDoPagamentoControl;

    AlunoDAO alunoDAO;
    List<Aluno> listaDeAlunos;
    MesDoPagamento mesDoPagamento;
    MesDoPagamentoDAO mesDoPagamentoDAO;

    Pagamento pagamento;
    PagamentoDAO pagamentoDAO;
    Verifica verifica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar_novo_mes_de_pagamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                CadastrarNovoMesDePagamento.this.finish();
                startActivity(new Intent(CadastrarNovoMesDePagamento.this, ListarPagamentos.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void botaoAdicionarNovoMesDePagamento(View view){

        mesDoPagamento = new MesDoPagamento();

        Spinner comboBoxSelecionarMesDePagamentos = findViewById(R.id.campoSelecioneMesDePagamentos);
        String mesDePagamentos = comboBoxSelecionarMesDePagamentos.getSelectedItem().toString();
        Spinner comboBoxSelecionarAnoDePagamentos = findViewById(R.id.campoSelecioneAnoDePagamentos);
        String anoDePagamentos = comboBoxSelecionarAnoDePagamentos.getSelectedItem().toString();
        mesDoPagamento.setMesEAnoDoPagamento(mesDePagamentos+" de "+anoDePagamentos);
        Spinner comboBoxSelecionarDiaDePagamentoVencimento = findViewById(R.id.selecioneDiaDoPagamentoVencimento);
        String diaDePagamentoVencimento = comboBoxSelecionarDiaDePagamentoVencimento.getSelectedItem().toString();
        Spinner comboBoxSelecionarMesDePagamentoVencimento = findViewById(R.id.selecioneMesDoPagamentoVencimento);
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
        Spinner comboBoxSelecionarAnoDePagamentoVencimento = findViewById(R.id.selecioneAnoDoPagamentoVencimento);
        String anoDePagamentoVencimento = comboBoxSelecionarAnoDePagamentoVencimento.getSelectedItem().toString();
        mesDoPagamento.setDataDoVencimento(diaDePagamentoVencimento+"/"+mesDePagamentoVencimento+"/"+anoDePagamentoVencimento);

        boolean jaExisteMesCadastrado = false;
        mesDoPagamentoDAO = new MesDoPagamentoDAO(this);
        List<MesDoPagamento> listaMesesPag = mesDoPagamentoDAO.listarMesesDoPagamento();
        for(int i=0; i<listaMesesPag.size();i++){
            if(listaMesesPag.get(i).getMesEAnoDoPagamento().equals(mesDoPagamento.getMesEAnoDoPagamento())){
                jaExisteMesCadastrado = true;
                break;
            }
        }

        if(jaExisteMesCadastrado){
            new PainelDeDialogo().mostrarMensagemDeErro("Erro", "Mês e Ano de pagamento já existente!", this);
        }else{
            MesDoPagamentoDAO mesPagDAO = new MesDoPagamentoDAO(this);
            mesPagDAO.inserirMesDoPagamento(mesDoPagamento);

            alunoDAO = new AlunoDAO(this);
            listaDeAlunos = alunoDAO.listarAlunos();

            pagamento = new Pagamento();
            pagamentoDAO = new PagamentoDAO(this);
            pagamento.setMesEAnoDoPagamento(mesDePagamentos+" de "+anoDePagamentos);
            pagamento.setDataDoPagamento("D");
            pagamento.setDataDoVencimento(diaDePagamentoVencimento+"/"+mesDePagamentoVencimento+"/"+anoDePagamentoVencimento);
            pagamento.setValorDoPagamento(0.0);
            pagamento.setStatus("Não Pago");
            pagamento.setNomeDoAluno("A");
            pagamento.setInstituicaoDeEnsinoDoAluno("I");
            pagamento.setNomeDoCobrador("C");
            pagamento.setNomeDoMotorista("M");
            pagamento.setObservacao("O");
            pagamentoDAO.inserirPagamento(pagamento, listaDeAlunos);

            AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CadastrarNovoMesDePagamento.this);
            caixaDeDialogo.setCancelable(false);
            caixaDeDialogo.setTitle("Mensagem");
            caixaDeDialogo.setMessage("Cadastro de novo mês de pagamento realizado com sucesso");
            caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialogInterface, int i) {
                    CadastrarNovoMesDePagamento.this.finish();
                    startActivity(new Intent(CadastrarNovoMesDePagamento.this, ListarPagamentos.class));
                }
            });
            caixaDeDialogo.create();
            caixaDeDialogo.show();
        }


    }
}
