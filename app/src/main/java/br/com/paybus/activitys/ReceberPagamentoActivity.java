package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.modelo.Motorista;
import br.com.paybus.modelo.Pagamento;

public class ReceberPagamentoActivity extends AppCompatActivity {

    List<String> listaCobradorPagamento;
    List<Cobrador> listaCobrador;
    CobradorDAO cobradorDAO;

    List<String> listaMotoristaPagamento;
    List<Motorista> listaMotorista;
    MotoristaDAO motoristaDAO;


    PagamentoDAO pagamentoDAO;
    Pagamento pagamento;

    Spinner comboBoxCobrador;
    Spinner comboBoxMotorista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_receber_pagamento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pagamento = new Pagamento();

        String textoAluno = getIntent().getStringExtra("nome_aluno_pagamento");
        TextView txtNomeAlunoPagamento = findViewById(R.id.txtNomeAlunoPagamento);
        txtNomeAlunoPagamento.setText(textoAluno);
        pagamento.setNomeDoAluno(textoAluno);

        String textoInstituicaoAluno = getIntent().getStringExtra("instituicao_aluno_pagamento");
        TextView txtInstituicaoAlunoPagamento = findViewById(R.id.txtInstituicaoAlunoPagamento);
        txtInstituicaoAlunoPagamento.setText(textoInstituicaoAluno);
        pagamento.setInstituicaoDeEnsinoDoAluno(textoInstituicaoAluno);

        cobradorDAO = new CobradorDAO(this);
        listaCobradorPagamento = new ArrayList<String>();
        listaCobradorPagamento.add("Selecione o(a) cobrador(a)");
        listaCobrador = cobradorDAO.listarCobradores();
        for(int i=0; i<listaCobrador.size(); i++){
            listaCobradorPagamento.add(listaCobrador.get(i).getNomeCompleto());
        }
        comboBoxCobrador = findViewById(R.id.comboSelecioneCobrador);
        ArrayAdapter<String> arrayAdapterCobrador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCobradorPagamento );
        arrayAdapterCobrador.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        comboBoxCobrador.setAdapter(arrayAdapterCobrador);

        motoristaDAO = new MotoristaDAO(this);
        listaMotoristaPagamento = new ArrayList<String>();
        listaMotoristaPagamento.add("Selecione o Motorista");
        listaMotorista = motoristaDAO.listarMotoristas();
        for(int i=0; i<listaMotorista.size(); i++){
            listaMotoristaPagamento.add(listaMotorista.get(i).getNomeCompleto());
        }
        comboBoxMotorista = findViewById(R.id.comboSelecioneMotorista);
        ArrayAdapter<String> arrayAdapterMotorista = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaMotoristaPagamento );
        arrayAdapterMotorista.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        comboBoxMotorista.setAdapter(arrayAdapterMotorista);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ReceberPagamentoActivity.this.finish();
                //startActivity(new Intent(ReceberPagamentoActivity.this, ListaDeAlunosPagamentos.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void botaoRealizarPagamentoDoAlunoSelecionado(View view){

        int idPagamento = getIntent().getIntExtra("id_pagamento", -1);
        pagamento.setId(idPagamento);

        String mesAnoPagamento = getIntent().getStringExtra("mes_e_ano_do_pagamento");
        pagamento.setMesEAnoDoPagamento(mesAnoPagamento);

        pagamento.setDataDoPagamento(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        String dataVencimento = getIntent().getStringExtra("data_do_vencimento");
        pagamento.setDataDoVencimento(dataVencimento);

        pagamento.setNomeDoCobrador(comboBoxCobrador.getSelectedItem().toString());

        pagamento.setNomeDoMotorista(comboBoxMotorista.getSelectedItem().toString());

        pagamento.setStatus("Pago");

        EditText valorAlunoPagamento = findViewById(R.id.campoValorAlunoPagamento);
        Double valorPagamento = Double.parseDouble(valorAlunoPagamento.getText().toString());
        pagamento.setValorDoPagamento(valorPagamento);

        EditText observacaoAlunoPagamento = findViewById(R.id.campoObservacaoAlunoPagamento);
        pagamento.setObservacao(observacaoAlunoPagamento.getText().toString());

        pagamentoDAO = new PagamentoDAO(this);
        pagamentoDAO.atualizarPagamento(pagamento);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentoActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Pagamento realizado com sucesso");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                ReceberPagamentoActivity.this.finish();
                startActivity(new Intent(ReceberPagamentoActivity.this, ListaDeAlunosPagamentos.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }




}
