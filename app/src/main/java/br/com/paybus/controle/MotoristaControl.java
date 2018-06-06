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
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Motorista;
import br.com.paybus.modelo.Pagamento;

public class MotoristaControl extends AppCompatActivity {


    Motorista motorista;
    MotoristaDAO dao;

    public void cadastrarMotorista(Context context){

        motorista = new Motorista();
        dao = new MotoristaDAO(this);

        // Cadastrando Aluno
        Motorista motoristaPagamento = new Motorista();

        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCompletoAluno);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoAluno);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAluno);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAluno);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAluno);
        EditText campoEmailAluno = findViewById(R.id.campoEmailAluno);

        motoristaPagamento.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        motoristaPagamento.setCpf(campoCPFAluno.getText().toString());
        motoristaPagamento.setEndereco(campoEnderecoAluno.getText().toString());
        motoristaPagamento.setTelefone(campoTelefoneAluno.getText().toString());
        motoristaPagamento.setSenha(campoCPFAluno.getText().toString());
        motoristaPagamento.setTipoDeUsuario("aluno");
        motoristaPagamento.setEmail(campoEmailAluno.getText().toString());

        dao.inserirMotorista(motoristaPagamento);

        Pagamento pagamento = new Pagamento();
        PagamentoDAO pagamentoDAO = new PagamentoDAO(MotoristaControl.this);
        pagamento.setMesEAnoDoPagamento(ListaDeAlunosPagamentos.mesDePagamento);
        pagamento.setDataDoPagamento("D");
        pagamento.setDataDoVencimento(ListaDeAlunosPagamentos.dataDeVencimento);
        pagamento.setValorDoPagamento(0.0);
        pagamento.setStatus("Não Pago");
        pagamento.setNomeDoAluno(motoristaPagamento.getNomeCompleto());
        pagamento.setNomeDoCobrador("C");
        pagamento.setNomeDoMotorista("M");
        pagamento.setObservacao("O");

        pagamentoDAO.inserirPagamento(pagamento);

        TelaPrincipalActivity.novoAlunoPagamento = "falso";

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(MotoristaControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Cadastro realizado com sucesso!\n\nNovo(a) aluno(a) adicionado(a) para a lista de pagamentos de "+ListaDeAlunosPagamentos.mesDePagamento);
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                MotoristaControl.this.finish();
                startActivity(new Intent(MotoristaControl.this, ListaDeAlunosPagamentos.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }

    int id;
    public void editarMotorista(Context context){


        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeAlunoEditar);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoEditar);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAlunoEditar);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAlunoEditar);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAlunoEditar);
        EditText campoEmailAluno = findViewById(R.id.campoEmailAlunoEditar);

        motorista = new Motorista();
        motorista.setId(id);
        motorista.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        motorista.setCpf(campoCPFAluno.getText().toString());
        motorista.setEndereco(campoEnderecoAluno.getText().toString());
        motorista.setTelefone(campoTelefoneAluno.getText().toString());
        motorista.setEmail(campoEmailAluno.getText().toString());

        dao = new MotoristaDAO(this);
        dao.atualizarMotorista(motorista);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(MotoristaControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(new Intent(MotoristaControl.this, ListaDeAlunosActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }


    public void listarMotoristas(Context context){
        dao.listarMotoristas();
    }

    public void excluirMotorista(Context context){
        dao.deletarMotorista(motorista);
    }



}
