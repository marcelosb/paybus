package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.paybus.R;
import br.com.paybus.controle.CobradorControl;
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.utilitarios.PainelDeDialogo;

public class EditarCobradorActivity extends AppCompatActivity {

    int id;
    CobradorControl cobradorControl;
    Cobrador cobrador;
    CobradorDAO dao;
    PainelDeDialogo painelDeDialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_cobrador);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#31a9b8")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra("id_cobrador", -1);

        String nomeCompleto = getIntent().getStringExtra("nome_completo_cobrador");
        EditText nomeCompletoDoCobrador = findViewById(R.id.campoNomeCobradorEditar);
        nomeCompletoDoCobrador.setText(nomeCompleto);

        String instituicaoCobrador = getIntent().getStringExtra("instituicao_cobrador");
        Spinner campoSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoCobradorEditar);
        for(int i=0; i<campoSelecionarInstituicao.getCount(); i++){
            campoSelecionarInstituicao.setSelection(i);
            if(campoSelecionarInstituicao.getSelectedItem().toString().equals(instituicaoCobrador)){
                campoSelecionarInstituicao.setSelection(i);
                break;
            }
        }

        String CPF = getIntent().getStringExtra("cpf_cobrador");
        EditText campoCPF = findViewById(R.id.campoCPFCobradorEditar);
        campoCPF.setText(CPF);

        String endereco = getIntent().getStringExtra("endereco_cobrador");
        EditText campoEndereco = findViewById(R.id.campoEnderecoCobradorEditar);
        campoEndereco.setText(endereco);

        String telefone = getIntent().getStringExtra("telefone_cobrador");
        EditText campoTelefone = findViewById(R.id.campoTelefoneCobradorEditar);
        campoTelefone.setText(telefone);

        String email = getIntent().getStringExtra("email_cobrador");
        EditText campoEmail = findViewById(R.id.campoEmailCobradorEditar);
        campoEmail.setText(email);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(EditarCobradorActivity.this, ListaDeCobradoresActivity.class));
                EditarCobradorActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void editarCobrador(View view){

        EditText campoNomeCompletoAluno = findViewById(R.id.campoNomeCobradorEditar);
        Spinner comboBoxSelecionarInstituicao = findViewById(R.id.comboBoxSelecionarInstituicaoCobradorEditar);
        EditText campoCPFAluno = findViewById(R.id.campoCPFCobradorEditar);
        EditText campoEnderecoAluno = findViewById(R.id.campoEnderecoCobradorEditar);
        EditText campoTelefoneAluno = findViewById(R.id.campoTelefoneCobradorEditar);
        EditText campoEmailAluno = findViewById(R.id.campoEmailCobradorEditar);

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

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(EditarCobradorActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(EditarCobradorActivity.this, ListaDeCobradoresActivity.class));
                EditarCobradorActivity.this.finish();
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();

    }


}
