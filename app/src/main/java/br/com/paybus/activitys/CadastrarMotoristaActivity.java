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

import br.com.paybus.R;
import br.com.paybus.controle.MotoristaControl;
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.modelo.Motorista;
import br.com.paybus.utilitarios.Verifica;

public class CadastrarMotoristaActivity extends AppCompatActivity {

    MotoristaControl motoristaControl;
    Motorista motorista;
    MotoristaDAO dao;
    Verifica verifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar_motorista);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#db9c1d")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(CadastrarMotoristaActivity.this, ListaDeMotoristasActivity.class));
                CadastrarMotoristaActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastrarMotoristaActivity.this, ListaDeMotoristasActivity.class));
        CadastrarMotoristaActivity.this.finish();
    }


    public void cadastrarMotorista(View view){
        motorista = new Motorista();
        dao = new MotoristaDAO(this);

        EditText campoNomeMotorista = findViewById(R.id.campoNomeMotorista);
        EditText campoCPFMotorista = findViewById(R.id.campoCPFMotorista);
        EditText campoEnderecoMotorista = findViewById(R.id.campoEnderecoMotorista);
        EditText campoCNHMotorista = findViewById(R.id.campoCNH);
        EditText campoTelefoneMotorista = findViewById(R.id.campoTelefoneMotorista);
        EditText campoEmailMotorista = findViewById(R.id.campoEmailMotorista);

        verifica = new Verifica();

        if(verifica.motorista(campoNomeMotorista.getText().toString(), campoCPFMotorista.getText().toString(),campoEmailMotorista.getText().toString(), this) ){
            motorista.setNomeCompleto(campoNomeMotorista.getText().toString());
            motorista.setCpf(campoCPFMotorista.getText().toString());
            motorista.setEndereco(campoEnderecoMotorista.getText().toString());
            motorista.setCnh(campoCNHMotorista.getText().toString());
            motorista.setTelefone(campoTelefoneMotorista.getText().toString());
            motorista.setSenha(campoCPFMotorista.getText().toString());
            motorista.setTipoDeUsuario("motorista");
            motorista.setEmail(campoEmailMotorista.getText().toString());

            dao.inserirMotorista(motorista);

            AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CadastrarMotoristaActivity.this);
            caixaDeDialogo.setCancelable(false);
            caixaDeDialogo.setTitle("Mensagem");
            caixaDeDialogo.setMessage("Cadastro realizado com sucesso");
            caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(CadastrarMotoristaActivity.this, ListaDeMotoristasActivity.class));
                    CadastrarMotoristaActivity.this.finish();
                }
            });
            caixaDeDialogo.create();
            caixaDeDialogo.show();
        }

    }


}
