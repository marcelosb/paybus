package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.paybus.R;
import br.com.paybus.controle.AlunoControl;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.utilitarios.Verifica;

public class CadastrarAlunoActivity extends AppCompatActivity {

    AlunoControl alunoControl;
    Aluno aluno;
    AlunoDAO dao;
    Verifica verifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar_aluno);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                CadastrarAlunoActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void cadastrarAluno(View view){
        aluno = new Aluno();
        dao = new AlunoDAO(this);

        if(TelaPrincipalActivity.novoAlunoPagamento.equals("verdadeiro")){

            // Cadastrando Aluno
            Aluno alunoPagamento = new Aluno();

            EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCompletoAluno);
            Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoAluno);
            EditText campoCPFAluno = findViewById(R.id.campoCPFAluno);
            EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAluno);
            EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAluno);
            EditText campoEmailAluno = findViewById(R.id.campoEmailAluno);

            alunoPagamento.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
            alunoPagamento.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
            alunoPagamento.setCpf(campoCPFAluno.getText().toString());
            alunoPagamento.setEndereco(campoEnderecoAluno.getText().toString());
            alunoPagamento.setTelefone(campoTelefoneAluno.getText().toString());
            alunoPagamento.setSenha(campoCPFAluno.getText().toString());
            alunoPagamento.setTipoDeUsuario("aluno");
            alunoPagamento.setEmail(campoEmailAluno.getText().toString());

            dao.inserirAluno(alunoPagamento);

            Pagamento pagamento = new Pagamento();
            PagamentoDAO pagamentoDAO = new PagamentoDAO(CadastrarAlunoActivity.this);
            pagamento.setMesEAnoDoPagamento(ListaDeAlunosPagamentos.mesDePagamento);
            pagamento.setDataDoPagamento("D");
            pagamento.setDataDoVencimento(ListaDeAlunosPagamentos.dataDeVencimento);
            pagamento.setValorDoPagamento(0.0);
            pagamento.setStatus("Não Pago");
            pagamento.setNomeDoAluno(alunoPagamento.getNomeCompleto());
            pagamento.setInstituicaoDeEnsinoDoAluno(alunoPagamento.getInstituicao());
            pagamento.setNomeDoCobrador("C");
            pagamento.setNomeDoMotorista("M");
            pagamento.setObservacao("O");

            pagamentoDAO.inserirPagamento(pagamento);

            TelaPrincipalActivity.novoAlunoPagamento = "falso";

            AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CadastrarAlunoActivity.this);
            caixaDeDialogo.setCancelable(false);
            caixaDeDialogo.setTitle("Mensagem");
            caixaDeDialogo.setMessage("Cadastro realizado com sucesso!\n\nNovo(a) aluno(a) adicionado(a) para a lista de pagamentos de "+ListaDeAlunosPagamentos.mesDePagamento);
            caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialogInterface, int i) {
                    CadastrarAlunoActivity.this.finish();
                    startActivity(new Intent(CadastrarAlunoActivity.this, ListaDeAlunosPagamentos.class));
                }
            });
            caixaDeDialogo.create();
            caixaDeDialogo.show();

        }else{
            EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCompletoAluno);
            Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoAluno);
            EditText campoCPFAluno = findViewById(R.id.campoCPFAluno);
            EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAluno);
            EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAluno);
            EditText campoEmailAluno = findViewById(R.id.campoEmailAluno);

            verifica = new Verifica();

            if(verifica.aluno(campoNomeCompletoAluno.getText().toString(), comboBoxSelecionarInstituicao.getSelectedItem().toString(), this) == true){
                aluno.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
                aluno.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
                aluno.setCpf(campoCPFAluno.getText().toString());
                aluno.setEndereco(campoEnderecoAluno.getText().toString());
                aluno.setTelefone(campoTelefoneAluno.getText().toString());
                aluno.setSenha(campoCPFAluno.getText().toString());
                aluno.setTipoDeUsuario("aluno");
                aluno.setEmail(campoEmailAluno.getText().toString());

                dao.inserirAluno(aluno);

                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CadastrarAlunoActivity.this);
                caixaDeDialogo.setCancelable(false);
                caixaDeDialogo.setTitle("Mensagem");
                caixaDeDialogo.setMessage("Cadastro realizado com sucesso");
                caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        CadastrarAlunoActivity.this.finish();
                        startActivity(new Intent(CadastrarAlunoActivity.this, ListaDeAlunosActivity.class));
                    }
                });
                caixaDeDialogo.create();
                caixaDeDialogo.show();
            }
        }
        

    }


}
