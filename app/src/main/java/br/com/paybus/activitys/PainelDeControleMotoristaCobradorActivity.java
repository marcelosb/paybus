package br.com.paybus.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.paybus.R;

public class PainelDeControleMotoristaCobradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle_cobrador_motorista);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_painel_de_controle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sobre:
                startActivity(new Intent(PainelDeControleMotoristaCobradorActivity.this, SobreActivity.class));
                break;
            case R.id.menu_alterar_senha:
                startActivity(new Intent(PainelDeControleMotoristaCobradorActivity.this, EditarSenhaDeUsuarioActivity.class));
                break;
            case R.id.menu_sair_do_sistema:
                finish();
                break;
             default:
                 break;
        }

        return super.onOptionsItemSelected(item);
    }



    public void irParaTelaListaDeAlunos(View view){
        startActivity(new Intent(this, ListaDeAlunosActivity.class));
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

}
