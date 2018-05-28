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
import br.com.paybus.controle.AlunoControl;
import br.com.paybus.controle.MotoristaControl;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Motorista;

public class EditarMotoristaActivity extends AppCompatActivity {

    int id;
    MotoristaControl motoristaControl;
    Motorista motorista;
    MotoristaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_motorista);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#db9c1d")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra("id_motorista", -1);

        String nomeCompleto = getIntent().getStringExtra("nome_completo_motorista");
        EditText nomeCompletoDoAluno = findViewById(R.id.campoNomeMotoristaEditar);
        nomeCompletoDoAluno.setText(nomeCompleto);

        String CPF = getIntent().getStringExtra("cpf_motorista");
        EditText campoCPF = findViewById(R.id.campoCPFMotoristaEditar);
        campoCPF.setText(CPF);

        String endereco = getIntent().getStringExtra("endereco_motorista");
        EditText campoEndereco = findViewById(R.id.campoEnderecoMotoristaEditar);
        campoEndereco.setText(endereco);

        String cnh = getIntent().getStringExtra("cnh");
        EditText campoCnh = findViewById(R.id.campoCNHMotoristaEditar);
        campoCnh.setText(cnh);

        String telefone = getIntent().getStringExtra("telefone_motorista");
        EditText campoTelefone = findViewById(R.id.campoTelefoneMotoristaEditar);
        campoTelefone.setText(telefone);

        String email = getIntent().getStringExtra("email_motorista");
        EditText campoEmail = findViewById(R.id.campoEmailMotoristaEditar);
        campoEmail.setText(email);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void editarMotorista(View view){

        EditText campoNomeCompleto = findViewById(R.id.campoNomeMotoristaEditar);
        EditText campoCPF = findViewById(R.id.campoCPFMotoristaEditar);
        EditText campoEndereco = findViewById(R.id.campoEnderecoMotoristaEditar);
        EditText campoCNH = findViewById(R.id.campoCNHMotoristaEditar);
        EditText campoTelefone = findViewById(R.id.campoTelefoneMotoristaEditar);
        EditText campoEmail = findViewById(R.id.campoEmailMotoristaEditar);

        motorista = new Motorista();
        motorista.setId(id);
        motorista.setNomeCompleto(campoNomeCompleto.getText().toString());
        motorista.setCpf(campoCPF.getText().toString());
        motorista.setEndereco(campoEndereco.getText().toString());
        motorista.setCnh(campoCNH.getText().toString());
        motorista.setTelefone(campoTelefone.getText().toString());
        motorista.setEmail(campoEmail.getText().toString());

        dao = new MotoristaDAO(this);
        dao.atualizarMotorista(motorista);

        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(EditarMotoristaActivity.this);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle("Mensagem");
        caixaDeDialogo.setMessage("Alteração realizada com sucesso!");
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(new Intent(EditarMotoristaActivity.this, ListaDeMotoristasActivity.class));
            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();

    }
}
