package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;

public class ReceberPagamentosActivity extends AppCompatActivity {

    AlunoDAO alunoDAO;
    List<String> listaDeNomes;
    EditText campoSelecionarInstituicaoPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_receber_pagamentos);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a937a")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alunoDAO = new AlunoDAO(ReceberPagamentosActivity.this);
        List<Aluno> listaDeAlunos = alunoDAO.listarAlunos();
        listaDeNomes = new ArrayList<String>();
        final List<String> listaDeInstituicoes = new ArrayList<String>();

        for(int i=0; i<listaDeAlunos.size(); i++){
            listaDeNomes.add(listaDeAlunos.get(i).getNomeCompleto());
            listaDeInstituicoes.add(listaDeAlunos.get(i).getInstituicao());
        }

        campoSelecionarInstituicaoPagamento = findViewById(R.id.campoSelecionarInstituicaoPagamento);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.campoNomeAutoCompletar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReceberPagamentosActivity.this, R.layout.support_simple_spinner_dropdown_item, listaDeNomes);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i=0; i<listaDeNomes.size(); i++){
                    if(listaDeNomes.get(i).equals(parent.getItemAtPosition(position).toString())){
                        campoSelecionarInstituicaoPagamento.setText(listaDeInstituicoes.get(i));
                        break;
                    }
                }

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void botaoRealizarPagamento(View view){
        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentosActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Pagamento realizado com sucesso!\n\nDeseja gerar o comprovante de pagamento?");
        caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {

                /**
                 * AQUI VAI O CÓDIGO QUE VAI GERAR O COMPROVANTE DE PAGAMENTO
                 * O DOCUMENTO EM PDF QUE PABLO PEDIU NA AULA
                 */

            }
        });
        caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }




}
