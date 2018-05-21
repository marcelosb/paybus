package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.paybus.R;
import br.com.paybus.controle.AlunoControl;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.utilitarios.PainelDeDialogo;

public class CadastrarAlunoActivity extends AppCompatActivity {

    AlunoControl alunoControl;
    Aluno aluno;
    AlunoDAO dao;
    PainelDeDialogo painelDeDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar_aluno);
    }

    public void cadastrarAlunoOuCobrador(View view){

        aluno = new Aluno();
        dao = new AlunoDAO(this);

        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCompletoAlunoOuCobrador);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicao);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAlunoOuCobrador);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAlunoOuCobrador);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAlunoOuCobrador);
        EditText campoSenhaAluno = findViewById(R.id.campoSenhaAlunoOuCobrador);

        aluno.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        aluno.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
        aluno.setCpf(campoCPFAluno.getText().toString());
        aluno.setEndereco(campoEnderecoAluno.getText().toString());
        aluno.setTelefone(campoTelefoneAluno.getText().toString());
        aluno.setSenha(campoSenhaAluno.getText().toString());
        aluno.setTipoDeUsuario("aluno");

        dao.inserirAluno(aluno);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CadastrarAlunoActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Cadastro realizado com sucesso");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                //finish();
                startActivity(new Intent(CadastrarAlunoActivity.this, ListaDeAlunosActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();


    }


}
