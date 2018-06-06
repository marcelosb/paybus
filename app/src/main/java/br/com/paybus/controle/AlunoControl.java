package br.com.paybus.controle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.acesso_banco_de_dados.ConexaoBD;
import br.com.paybus.activitys.CadastrarAlunoActivity;
import br.com.paybus.activitys.EditarAlunoActivity;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.activitys.ListaDeAlunosPagamentos;
import br.com.paybus.activitys.TelaPrincipalActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.utilitarios.Verifica;

public class AlunoControl extends AppCompatActivity{

    Aluno aluno;
    AlunoDAO dao;

    public void cadastrarAluno(Context context){

        aluno = new Aluno();
        dao = new AlunoDAO(this);

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
        PagamentoDAO pagamentoDAO = new PagamentoDAO(AlunoControl.this);
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

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(AlunoControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Cadastro realizado com sucesso!\n\nNovo(a) aluno(a) adicionado(a) para a lista de pagamentos de "+ListaDeAlunosPagamentos.mesDePagamento);
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                AlunoControl.this.finish();
                startActivity(new Intent(AlunoControl.this, ListaDeAlunosPagamentos.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }

    int id;
    public void editarAluno(Context context){


        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeAlunoEditar);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoEditar);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAlunoEditar);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAlunoEditar);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAlunoEditar);
        EditText campoEmailAluno = findViewById(R.id.campoEmailAlunoEditar);

        aluno = new Aluno();
        aluno.setId(id);
        aluno.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        aluno.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
        aluno.setCpf(campoCPFAluno.getText().toString());
        aluno.setEndereco(campoEnderecoAluno.getText().toString());
        aluno.setTelefone(campoTelefoneAluno.getText().toString());
        aluno.setEmail(campoEmailAluno.getText().toString());

        dao = new AlunoDAO(this);
        dao.atualizarAluno(aluno);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(AlunoControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(new Intent(AlunoControl.this, ListaDeAlunosActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }


    public void listarAlunos(Context context){
        dao.listarAlunos();
    }

    public void excluirAluno(Context context){
        dao.deletarAluno(aluno);
    }



}
