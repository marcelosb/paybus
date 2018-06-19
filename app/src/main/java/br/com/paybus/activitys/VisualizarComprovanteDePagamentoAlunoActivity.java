package br.com.paybus.activitys;

import android.Manifest;
import android.app.Activity;
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
import android.widget.TextView;

import br.com.paybus.R;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.modelo.Pagamentos;
import br.com.paybus.utilitarios.ComprovanteDePagamentoAlunoRecyclerViewAdapter;
import br.com.paybus.utilitarios.GerarPDFPagamento;

public class VisualizarComprovanteDePagamentoAlunoActivity extends AppCompatActivity {

    //importante!!  este objeto deve ser global para entrar nos metodos
    GerarPDFPagamento gerarPDF;

    //importante!!  esta variavel deve ser global para entar nos metodos
    Pagamento pagamento;

    //codigo para pedido de permissao
    public static final int REQUEST_PERMISSIONS_CODE = 128;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_visualizar_comprovante_de_pagamento_aluno);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String teste = getIntent().getStringExtra("mes_e_ano_do_comprovante_de_pagamento");
        TextView txtMesEAnoCP = findViewById(R.id.txtMesEAnoCP);
        txtMesEAnoCP.setText(getIntent().getStringExtra("mes_e_ano_do_comprovante_de_pagamento"));

        TextView txtMotoristaCP = findViewById(R.id.txtMotoristaCP);
        //txtMotoristaCP.setText("Motorista: "+getIntent().getStringExtra("motorista_comprovante_de_pagamento"));
        txtMotoristaCP.setText("Motorista: "+getIntent().getStringExtra("motorista_comprovante_de_pagamento"));

        TextView txtCobradorCP = findViewById(R.id.txtCobradorCP);
        txtCobradorCP.setText("Cobrador(a): "+getIntent().getStringExtra("cobrador_comprovante_de_pagamento"));

        TextView txtEstudanteCP = findViewById(R.id.txtEstudanteCP);
        txtEstudanteCP.setText("Estudante: "+getIntent().getStringExtra("estudante_comprovante_de_pagamento"));

        TextView txtInstituicaoAlunoCP = findViewById(R.id.txtInstituicaoAlunoCP);
        txtInstituicaoAlunoCP.setText("Instituição de Ensino: "+getIntent().getStringExtra("instituicao_estudante_comprovante_de_pagamento"));

        TextView txtDataDoPagamentoCP = findViewById(R.id.txtDataDoPagamentoCP);
        txtDataDoPagamentoCP.setText("Data do Pagamento: "+getIntent().getStringExtra("data_do_pagamento_comprovante_de_pagamento"));

        TextView txtDataDoVencimentoCP = findViewById(R.id.txtDataDoVencimentoCP);
        txtDataDoVencimentoCP.setText("Data do Vencimento: "+getIntent().getStringExtra("data_do_vencimento_comprovante_de_pagamento"));

        TextView txtPagamentoCP = findViewById(R.id.txtPagamentoCP);
        txtPagamentoCP.setText("Valor do Pagamento: "+getIntent().getDoubleExtra("valor_comprovante_de_pagamento", -1.0));

        TextView txtObservacaoCP = findViewById(R.id.txtObservacaoCP);
        txtObservacaoCP.setText("Observação: "+getIntent().getStringExtra("observacao_comprovante_de_pagamento"));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(VisualizarComprovanteDePagamentoAlunoActivity.this, PainelDeControleAlunoActivity.class));
                VisualizarComprovanteDePagamentoAlunoActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VisualizarComprovanteDePagamentoAlunoActivity.this, PainelDeControleAlunoActivity.class));
        VisualizarComprovanteDePagamentoAlunoActivity.this.finish();
    }

    public void botaoGerarPdfComprovanteAluno(View view){
        //iniciando a variavel gerar pdf
        gerarPDF = new GerarPDFPagamento(getApplicationContext());
        //inicinado o objeto pagamento
        //pagamento = new Pagamentos("20/05/2017","25/05/2017", 100.00, "pago", "Luan Ramons Silva Linhares", "Isabela Ferreira Andrade","José Anchieta Gomes" , "Pagou com atraso, não foi cobrado nenhum valor adicional");

        pagamento = new Pagamento();
        pagamento.setMesEAnoDoPagamento(getIntent().getStringExtra("mes_e_ano_do_comprovante_de_pagamento"));
        pagamento.setNomeDoMotorista(getIntent().getStringExtra("motorista_comprovante_de_pagamento"));
        pagamento.setNomeDoCobrador(getIntent().getStringExtra("cobrador_comprovante_de_pagamento"));
        pagamento.setNomeDoAluno(getIntent().getStringExtra("estudante_comprovante_de_pagamento"));
        pagamento.setInstituicaoDeEnsinoDoAluno(getIntent().getStringExtra("instituicao_estudante_comprovante_de_pagamento"));
        pagamento.setDataDoPagamento(getIntent().getStringExtra("data_do_pagamento_comprovante_de_pagamento"));
        pagamento.setDataDoVencimento(getIntent().getStringExtra("data_do_vencimento_comprovante_de_pagamento"));
        pagamento.setValorDoPagamento(getIntent().getDoubleExtra("valor_comprovante_de_pagamento", -1.0));
        pagamento.setObservacao(getIntent().getStringExtra("observacao_comprovante_de_pagamento"));

        chamarPermissaoGravacao(pagamento);
    }


    //metodo que faz a verificação se ja tem permissao ou não para gravação e criação na memoria
    public void chamarPermissaoGravacao(Pagamento pagamento) {

        //verifica a permição e se o usuario ja negou alguma vez essa permição
        if( ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ){

            //caso ja tenha negado, esse if serve para pedir novamente, com uma msg especial criada pelo progrmador
            //infromando a importancia desta função para o aplicativo;
            if( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) ){
                pedirPermissao( "É preciso a sua permissão para exibir o comprovante de pagamento.\nPara continuar por favor aceite os termos.", new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE} );
            }

            //caso nao seja necessario pedir novamente ele ja manda a permissão pra ser aceita
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE );
            }
        }

        //ja tiver sido aceita a permissão , executa o metodo direto de geração de pdf
        else{
            gerarPDF.criarPDFComprovanteDePagamentoAluno(pagamento);
        }

    }


    //metodo para pedir permissao ao usuario se ele deseja ou n liberar para gerar documento e escrever
    private void pedirPermissao( String message, final String[] permissions ){
        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Permissão");
        caixaDeDialogo.setMessage(message);
        caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                //se o usuario aceitar, ele manda a permissao para ser aceita
                ActivityCompat.requestPermissions(VisualizarComprovanteDePagamentoAlunoActivity.this, permissions, REQUEST_PERMISSIONS_CODE);

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
