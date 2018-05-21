package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
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

public class EditarAlunoActivity extends AppCompatActivity {

    int id;
    AlunoControl alunoControl;
    Aluno aluno;
    AlunoDAO dao;
    PainelDeDialogo painelDeDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_aluno);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF8800")));

        id = getIntent().getIntExtra("id_aluno", -1);

        String nomeCompleto = getIntent().getStringExtra("nome_completo_aluno");
        EditText nomeCompletoDoAluno = findViewById(R.id.campoNomeCompletoAlunoOuCobradorEditar);
        nomeCompletoDoAluno.setText(nomeCompleto);

        String instituicaoAluno = getIntent().getStringExtra("instituicao_aluno");
        Spinner campoSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoEditar);
        for(int i=0; i<campoSelecionarInstituicao.getCount(); i++){
            campoSelecionarInstituicao.setSelection(i);
            if(campoSelecionarInstituicao.getSelectedItem().toString().equals(instituicaoAluno)){
                campoSelecionarInstituicao.setSelection(i);
                break;
            }
        }

        String CPF = getIntent().getStringExtra("cpf_aluno");
        EditText campoCPF = findViewById(R.id.campoCPFAlunoOuCobradorEditar);
        campoCPF.setText(CPF);

        String endereco = getIntent().getStringExtra("endereco_aluno");
        EditText campoEndereco = findViewById(R.id.campoEnderecoAlunoOuCobradorEditar);
        campoEndereco.setText(endereco);

        String telefone = getIntent().getStringExtra("telefone_aluno");
        EditText campoTelefone = findViewById(R.id.campoTelefoneAlunoOuCobradorEditar);
        campoTelefone.setText(telefone);

        String senha = getIntent().getStringExtra("senha_aluno");
        EditText campoSenha = findViewById(R.id.campoSenhaAlunoOuCobradorEditar);
        campoSenha.setText(senha);

    }

    public void editarAlunoOuCobrador(View view){

        aluno = new Aluno();
        dao = new AlunoDAO(this);

        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCompletoAlunoOuCobradorEditar);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoEditar);
        EditText campoCPFAluno = findViewById(R.id.campoCPFAlunoOuCobradorEditar);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoAlunoOuCobradorEditar);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneAlunoOuCobradorEditar);
        EditText campoSenhaAluno = findViewById(R.id.campoSenhaAlunoOuCobradorEditar);

        aluno.setId(id);
        aluno.setNomeCompleto(campoNomeCompletoAluno.getText().toString());
        aluno.setInstituicao(comboBoxSelecionarInstituicao.getSelectedItem().toString());
        aluno.setCpf(campoCPFAluno.getText().toString());
        aluno.setEndereco(campoEnderecoAluno.getText().toString());
        aluno.setTelefone(campoTelefoneAluno.getText().toString());
        aluno.setSenha(campoSenhaAluno.getText().toString());


        dao.atualizarAluno(aluno);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(EditarAlunoActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                //finish();
                startActivity(new Intent(EditarAlunoActivity.this, ListaDeAlunosActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();

    }


}
