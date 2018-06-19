package br.com.paybus.activitys;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamentos;
import br.com.paybus.utilitarios.GerarPDFPagamento;


public class ReceberPagamentosActivity extends AppCompatActivity {


    AlunoDAO alunoDAO;
    List<String> listaDeNomes;
    EditText campoSelecionarInstituicaoPagamento;

    //importante!!  este objeto deve ser global para entrar nos metodos
    GerarPDFPagamento gerarPDF;
    //importante!!  esta variavel deve ser global para entar nos metodos
    Pagamentos pagamento;

    //codigo para pedido de permissao
    public static final int REQUEST_PERMISSIONS_CODE = 128;

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

    public void botaoRealizarPagamento( View view){
        //iniciando a variavel gerar pdf
        gerarPDF = new GerarPDFPagamento(getApplicationContext());
        //inicinado o objeto pagamento
        pagamento = new Pagamentos("20/05/2017","25/05/2017", 100.00, "pago", "Luan Ramons Silva Linhares", "Isabela Ferreira Andrade","José Anchieta Gomes" , "Pagou com atraso, não foi cobrado nenhum valor adicional");

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentosActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Pagamento realizado com sucesso!\n\nDeseja gerar o comprovante de pagamento?");
        caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                chamarPermissaoGravacao(pagamento);
            }
        });
        caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }

    public void adicionarNovoAluno(View view){
        ReceberPagamentosActivity.this.finish();
        startActivity(new Intent(ReceberPagamentosActivity.this, CadastrarAlunoActivity.class));
    }



    //sobrescrita do metodo que verifica se tem permissao para criar arquivos no android
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch( requestCode ){
            case REQUEST_PERMISSIONS_CODE:
                for( int i = 0; i < permissions.length; i++ ){

                    //caso ja tenha a permissao, só criar e escrever o arquivo
                    if( permissions[i].equalsIgnoreCase( Manifest.permission.WRITE_EXTERNAL_STORAGE )
                            && grantResults[i] == PackageManager.PERMISSION_GRANTED ){
                        //gerarPDF.criarPDF(pagamento);

                    }

                }
        }
        //manda para a superclasse a permissao
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    //metodo para pedir permissao ao usuario se ele deseja ou n liberar para gerar documento e escrever
    private void pedirPermissao( String message, final String[] permissions ){
        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(ReceberPagamentosActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Permissão");
        caixaDeDialogo.setMessage(message);
        caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                //se o usuario aceitar, ele manda a permissao para ser aceita
                ActivityCompat.requestPermissions(ReceberPagamentosActivity.this, permissions, REQUEST_PERMISSIONS_CODE);

            }
        });
        caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();

    }

    //metodo que faz a verificação se ja tem permissao ou não para gravação e criação na memoria
    public void chamarPermissaoGravacao(Pagamentos pagamento) {

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
            //gerarPDF.criarPDF(pagamento);

        }

    }


}
