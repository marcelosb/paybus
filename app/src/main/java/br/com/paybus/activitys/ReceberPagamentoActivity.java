package br.com.paybus.activitys;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import br.com.paybus.modelo.Pagamentos;
import br.com.paybus.utilitarios.GerarPDFPagamento;
import br.com.paybus.utilitarios.PagamentoAlunoRecyclerViewAdapter;
import br.com.paybus.utilitarios.PainelDeDialogo;

public class ReceberPagamentoActivity extends AppCompatActivity {

    //importante!!  este objeto deve ser global para entrar nos metodos
    GerarPDFPagamento gerarPDF;

    //importante!!  esta variavel deve ser global para entar nos metodos
    Pagamentos pagamentos;

    //codigo para pedido de permissao
    public static final int REQUEST_PERMISSIONS_CODE = 128;

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


        if(PagamentoAlunoRecyclerViewAdapter.editarPagamento) {
            Button botaoEditarReceberPagamento = findViewById(R.id.botaoEditarReceberPagamento);
            botaoEditarReceberPagamento.setText("EDITAR PAGAMENTO");
            this.setTitle("Editar Pagamento");

            String selecaoCobrador = getIntent().getStringExtra("cobrador_selecao");
            for(int i=0; i<comboBoxCobrador.getCount(); i++){
                comboBoxCobrador.setSelection(i);
                if(comboBoxCobrador.getSelectedItem().toString().equals(selecaoCobrador)){
                    comboBoxCobrador.setSelection(i);
                    break;
                }
            }

            String selecaoMotorista = getIntent().getStringExtra("motorista_selecao");
            for(int i=0; i<comboBoxMotorista.getCount(); i++){
                comboBoxMotorista.setSelection(i);
                if(comboBoxMotorista.getSelectedItem().toString().equals(selecaoMotorista)){
                    comboBoxMotorista.setSelection(i);
                    break;
                }
            }

            Double valorPag = getIntent().getDoubleExtra("valor_pagamento", -1.0);
            EditText valorAlunoPagamento = findViewById(R.id.campoValorAlunoPagamento);
            valorAlunoPagamento.setText(String.valueOf(valorPag.intValue()));

            String observacaoEdit = getIntent().getStringExtra("observacao_pagamento");
            EditText observacaoAlunoPagamento = findViewById(R.id.campoObservacaoAlunoPagamento);
            observacaoAlunoPagamento.setText(observacaoEdit);


        }else if(PagamentoAlunoRecyclerViewAdapter.editarPagamento == false){
            Button botaoEditarReceberPagamento = findViewById(R.id.botaoEditarReceberPagamento);
            botaoEditarReceberPagamento.setText("RECEBER PAGAMENTO");
            this.setTitle("Receber Pagamento");

            comboBoxMotorista.setSelection(0);
            comboBoxCobrador.setSelection(0);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(ReceberPagamentoActivity.this, ListaDeAlunosPagamentos.class));
                ReceberPagamentoActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void botaoRealizarPagamentoDoAlunoSelecionado(View view){

        if(PagamentoAlunoRecyclerViewAdapter.editarPagamento){

            PagamentoAlunoRecyclerViewAdapter.editarPagamento = false;

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

            EditText observacaoAlunoPagamento = findViewById(R.id.campoObservacaoAlunoPagamento);
            pagamento.setObservacao(observacaoAlunoPagamento.getText().toString());

            EditText valorAlunoPagamento = findViewById(R.id.campoValorAlunoPagamento);

            if(pagamento.getNomeDoCobrador().equals("Selecione o(a) cobrador(a)")){
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Selecione um cobrador." , this);
            }else if(pagamento.getNomeDoMotorista().equals("Selecione o Motorista")){
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Selecione um motorista." , this);
            }
            else if(valorAlunoPagamento.getText().toString().isEmpty()){
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Insira o valor do pagamento." , this);
            }else{

                Double valorPagamento = Double.parseDouble(valorAlunoPagamento.getText().toString());
                pagamento.setValorDoPagamento(valorPagamento);

                pagamentoDAO = new PagamentoDAO(this);
                pagamentoDAO.atualizarPagamento(pagamento);

                //iniciando a variavel gerar pdf
                gerarPDF = new GerarPDFPagamento(getApplicationContext());
                //inicinado o objeto pagamento
                //pagamento = new Pagamentos("20/05/2017","25/05/2017", 100.00, "pago", "Luan Ramons Silva Linhares", "Isabela Ferreira Andrade","José Anchieta Gomes" , "Pagou com atraso, não foi cobrado nenhum valor adicional");

                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentoActivity.this);
                caixaDeDialogo.setCancelable(false);
                caixaDeDialogo.setTitle("Mensagem");
                caixaDeDialogo.setMessage("Pagamento editado com sucesso!\n\nDeseja gerar o comprovante de pagamento?");
                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        chamarPermissaoGravacao(pagamento);
                        ReceberPagamentoActivity.this.finish();
                    }
                });
                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(ReceberPagamentoActivity.this, ListaDeAlunosPagamentos.class));
                        ReceberPagamentoActivity.this.finish();
                    }
                });
                caixaDeDialogo.create();
                caixaDeDialogo.show();
            }


        }else{

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

            EditText observacaoAlunoPagamento = findViewById(R.id.campoObservacaoAlunoPagamento);
            pagamento.setObservacao(observacaoAlunoPagamento.getText().toString());

            EditText valorAlunoPagamento = findViewById(R.id.campoValorAlunoPagamento);

            if(pagamento.getNomeDoCobrador().equals("Selecione o(a) cobrador(a)")){
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Selecione um cobrador." , this);
            }else if(pagamento.getNomeDoMotorista().equals("Selecione o Motorista")){
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Selecione um motorista." , this);
            }
            else if(valorAlunoPagamento.getText().toString().isEmpty()){
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Insira o valor do pagamento." , this);
            }
            else{

                Double valorPagamento = Double.parseDouble(valorAlunoPagamento.getText().toString());
                pagamento.setValorDoPagamento(valorPagamento);

                pagamentoDAO = new PagamentoDAO(this);
                pagamentoDAO.atualizarPagamento(pagamento);

                //iniciando a variavel gerar pdf
                gerarPDF = new GerarPDFPagamento(getApplicationContext());
                //inicinado o objeto pagamento
                //pagamento = new Pagamentos("20/05/2017","25/05/2017", 100.00, "pago", "Luan Ramons Silva Linhares", "Isabela Ferreira Andrade","José Anchieta Gomes" , "Pagou com atraso, não foi cobrado nenhum valor adicional");

                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentoActivity.this);
                caixaDeDialogo.setCancelable(false);
                caixaDeDialogo.setTitle("Mensagem");
                caixaDeDialogo.setMessage("Pagamento realizado com sucesso!\n\nDeseja gerar o comprovante de pagamento?");
                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        chamarPermissaoGravacao(pagamento);
                        ReceberPagamentoActivity.this.finish();
                    }
                });
                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(ReceberPagamentoActivity.this, ListaDeAlunosPagamentos.class));
                        ReceberPagamentoActivity.this.finish();
                    }
                });
                caixaDeDialogo.create();
                caixaDeDialogo.show();
            }

        }

    }


    //metodo que faz a verificação se ja tem permissao ou não para gravação e criação na memoria
    public void chamarPermissaoGravacao(Pagamento pagamento) {

        //verifica a permição e se o usuario ja negou alguma vez essa permição
        if( ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ){

            //caso ja tenha negado, esse if serve para pedir novamente, com uma msg especial criada pelo progrmador
            //infromando a importancia desta função para o aplicativo;
            if( ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) ){
                pedirPermissao( "É preciso a sua permissão para exibir o comprovante de pagamento.\nPara continuar por favor aceite os termos.", new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} );
            }

            //caso nao seja necessario pedir novamente ele ja manda a permissão pra ser aceita
            else{
                ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE );
            }
        }

        //ja tiver sido aceita a permissão , executa o metodo direto de geração de pdf
        else{
            gerarPDF.criarPDF(pagamento);
        }

    }

    //metodo para pedir permissao ao usuario se ele deseja ou n liberar para gerar documento e escrever
    private void pedirPermissao( String message, final String[] permissions ){
        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentoActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Permissão");
        caixaDeDialogo.setMessage(message);
        caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                //se o usuario aceitar, ele manda a permissao para ser aceita
                ActivityCompat.requestPermissions(ReceberPagamentoActivity.this, permissions, REQUEST_PERMISSIONS_CODE);

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
