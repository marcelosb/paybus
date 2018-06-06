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
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.modelo.Pagamento;

public class CobradorControl extends AppCompatActivity {

    Cobrador cobrador;
    CobradorDAO dao;

    public void cadastrarCobrador(Context context){

        cobrador = new Cobrador();
        dao = new CobradorDAO(this);

        // Cadastrando Aluno
        Cobrador cobradorPagamento = new Cobrador();

        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCompletoAluno);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoAluno);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAluno);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAluno);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAluno);
        EditText campoEmailAluno = findViewById(R.id.campoEmailAluno);

        cobradorPagamento.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        cobradorPagamento.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
        cobradorPagamento.setCpf(campoCPFAluno.getText().toString());
        cobradorPagamento.setEndereco(campoEnderecoAluno.getText().toString());
        cobradorPagamento.setTelefone(campoTelefoneAluno.getText().toString());
        cobradorPagamento.setSenha(campoCPFAluno.getText().toString());
        cobradorPagamento.setTipoDeUsuario("aluno");
        cobradorPagamento.setEmail(campoEmailAluno.getText().toString());

        dao.inserirCobrador(cobradorPagamento);

        Pagamento pagamento = new Pagamento();
        PagamentoDAO pagamentoDAO = new PagamentoDAO(CobradorControl.this);
        pagamento.setMesEAnoDoPagamento(ListaDeAlunosPagamentos.mesDePagamento);
        pagamento.setDataDoPagamento("D");
        pagamento.setDataDoVencimento(ListaDeAlunosPagamentos.dataDeVencimento);
        pagamento.setValorDoPagamento(0.0);
        pagamento.setStatus("Não Pago");
        pagamento.setNomeDoAluno(cobradorPagamento.getNomeCompleto());
        pagamento.setInstituicaoDeEnsinoDoAluno(cobradorPagamento.getInstituicao());
        pagamento.setNomeDoCobrador("C");
        pagamento.setNomeDoMotorista("M");
        pagamento.setObservacao("O");

        pagamentoDAO.inserirPagamento(pagamento);

        TelaPrincipalActivity.novoAlunoPagamento = "falso";

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CobradorControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Cadastro realizado com sucesso!\n\nNovo(a) aluno(a) adicionado(a) para a lista de pagamentos de "+ListaDeAlunosPagamentos.mesDePagamento);
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                CobradorControl.this.finish();
                startActivity(new Intent(CobradorControl.this, ListaDeAlunosPagamentos.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }

    int id;
    public void editarCobrador(Context context){


        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeAlunoEditar);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoEditar);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAlunoEditar);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAlunoEditar);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAlunoEditar);
        EditText campoEmailAluno = findViewById(R.id.campoEmailAlunoEditar);

        cobrador = new Cobrador();
        cobrador.setId(id);
        cobrador.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        cobrador.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
        cobrador.setCpf(campoCPFAluno.getText().toString());
        cobrador.setEndereco(campoEnderecoAluno.getText().toString());
        cobrador.setTelefone(campoTelefoneAluno.getText().toString());
        cobrador.setEmail(campoEmailAluno.getText().toString());

        dao = new CobradorDAO(this);
        dao.atualizarCobrador(cobrador);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CobradorControl.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(new Intent(CobradorControl.this, ListaDeAlunosActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }


    public void listarCobradores(Context context){
        dao.listarCobradores();
    }

    public void excluirCobrador(Context context){
        dao.deletarCobrador(cobrador);
    }
}
