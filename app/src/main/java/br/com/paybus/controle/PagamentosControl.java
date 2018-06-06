package br.com.paybus.controle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.paybus.R;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.activitys.ListaDeAlunosPagamentos;
import br.com.paybus.activitys.TelaPrincipalActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamento;

public class PagamentosControl extends AppCompatActivity {

    Pagamento pagamento;
    PagamentoDAO dao;
    AlunoDAO alunoDAO;

    public void cadastrarPagamento(Context context){

        pagamento = new Pagamento();
        dao = new PagamentoDAO(this);

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

        alunoDAO.inserirAluno(alunoPagamento);

        Pagamento pagamento = new Pagamento();
        PagamentoDAO pagamentoDAO = new PagamentoDAO(PagamentosControl.this);
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

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(PagamentosControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Cadastro realizado com sucesso!\n\nNovo(a) aluno(a) adicionado(a) para a lista de pagamentos de "+ListaDeAlunosPagamentos.mesDePagamento);
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                PagamentosControl.this.finish();
                startActivity(new Intent(PagamentosControl.this, ListaDeAlunosPagamentos.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }

    int id;
    Aluno alunos;
    public void editarPagamento(Context context){

        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeAlunoEditar);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoEditar);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAlunoEditar);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAlunoEditar);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAlunoEditar);
        EditText campoEmailAluno = findViewById(R.id.campoEmailAlunoEditar);

        alunos = new Aluno();
        alunos.setId(id);
        alunos.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        alunos.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
        alunos.setCpf(campoCPFAluno.getText().toString());
        alunos.setEndereco(campoEnderecoAluno.getText().toString());
        alunos.setTelefone(campoTelefoneAluno.getText().toString());
        alunos.setEmail(campoEmailAluno.getText().toString());

        alunoDAO = new AlunoDAO(this);
        alunoDAO.inserirAluno(alunos);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(PagamentosControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(new Intent(PagamentosControl.this, ListaDeAlunosActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }


    public void listarPagamentos(Context context){
        dao.listarPagamentosDosAlunosPorMes("teste");
    }

    public void excluirPagamento(Context context){
        dao.deletarPagamento(pagamento);
    }




}
