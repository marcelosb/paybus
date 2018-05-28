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
import br.com.paybus.controle.CobradorControl;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.utilitarios.Verifica;

public class CadastrarCobradorActivity extends AppCompatActivity {

    CobradorControl cobradorControl;
    Cobrador cobrador;
    CobradorDAO dao;
    Verifica verifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastrar_cobrador);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#31a9b8")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void cadastrarCobrador(View view){
        cobrador = new Cobrador();
        dao = new CobradorDAO(this);

        EditText campoNomeCompletoCobrador = findViewById(R.id.campoNomeCompletoCobrador);
        Spinner comboBoxSelecionarInstituicaoCobrador = findViewById(R.id.comboBoxSelecionarInstituicaoCobrador);
        EditText campoCPFCobrador = findViewById(R.id.campoCPFCobrador);
        EditText campoEnderecoCobrador = findViewById(R.id.campoEnderecoCobrador);
        EditText campoTelefoneCobrador = findViewById(R.id.campoTelefoneCobrador);
        EditText campoEmailCobrador = findViewById(R.id.campoEmailCobrador);

        verifica = new Verifica();

        if(verifica.cobrador(campoNomeCompletoCobrador.getText().toString(), comboBoxSelecionarInstituicaoCobrador.getSelectedItem().toString(), this) == true){
            cobrador.setNomeCompleto(campoNomeCompletoCobrador.getText().toString());
            cobrador.setInstituicao(comboBoxSelecionarInstituicaoCobrador.getSelectedItem().toString());
            cobrador.setCpf(campoCPFCobrador.getText().toString());
            cobrador.setEndereco(campoEnderecoCobrador.getText().toString());
            cobrador.setTelefone(campoTelefoneCobrador.getText().toString());
            cobrador.setSenha(campoCPFCobrador.getText().toString());
            cobrador.setTipoDeUsuario("cobrador");
            cobrador.setEmail(campoEmailCobrador.getText().toString());

            dao.inserirCobrador(cobrador);

            AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(CadastrarCobradorActivity.this);
            caixaDeDialogo.setCancelable(false);
            caixaDeDialogo.setTitle("Mensagem");
            caixaDeDialogo.setMessage("Cadastro realizado com sucesso");
            caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    startActivity(new Intent(CadastrarCobradorActivity.this, ListaDeCobradoresActivity.class));
                }
            });
            caixaDeDialogo.create();
            caixaDeDialogo.show();
        }

    }

}
