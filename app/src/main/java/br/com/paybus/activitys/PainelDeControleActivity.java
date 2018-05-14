package br.com.paybus.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import br.com.paybus.R;

public class PainelDeControleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_painel_de_controle, menu);
        return true;
    }

    public void irParaTelaListaDeAlunos(View view){
        startActivity(new Intent(this, ListaDeAlunosActivity.class));
    }

    public void irParaTelaDadosDoMotorista(View view){
        startActivity(new Intent(this, MotoristaActivity.class));
    }

    public void irParaTelaInadimplentes(View view){
        startActivity(new Intent(this, InadimplentesActivity.class));
    }

    public void irParaTelaReceberPagamentos(View view){
        startActivity(new Intent(this, ReceberPagamentosActivity.class));
    }

    public void irParaTelaRelatorios(View view){
        startActivity(new Intent(this, RelatoriosActivity.class));
    }

    public void irParaTelaSobre(View view){
        startActivity(new Intent(this, SobreActivity.class));
    }

}
