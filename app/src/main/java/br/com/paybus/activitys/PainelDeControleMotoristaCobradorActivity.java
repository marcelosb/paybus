package br.com.paybus.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.paybus.R;
import br.com.paybus.utilitarios.Usuario;

public class PainelDeControleMotoristaCobradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle_cobrador_motorista);

        if( Usuario.tipo.equals("cobrador") ){
            TextView textoNomeCobradorlogado = findViewById(R.id.textoNomeCobradorOuMotoristaLogado);
            setTitle("Painel de Controle Cobrador");
            textoNomeCobradorlogado.setText("Olá "+Usuario.nome+", seja bem vindo!");
        }else if( Usuario.tipo.equals("motorista") ){
            TextView textoNomeMotoristalogado = findViewById(R.id.textoNomeCobradorOuMotoristaLogado);
            setTitle("Painel de Controle Motorista");
            textoNomeMotoristalogado.setText("Olá "+Usuario.nome+", seja bem vindo!");
        }

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
                startActivity(new Intent(PainelDeControleMotoristaCobradorActivity.this, ViewPDFAjudaActivity.class));
                PainelDeControleMotoristaCobradorActivity.this.finish();
                break;
            case R.id.menu_alterar_senha:
                startActivity(new Intent(PainelDeControleMotoristaCobradorActivity.this, EditarSenhaDeUsuarioActivity.class));
                PainelDeControleMotoristaCobradorActivity.this.finish();
                break;
            case R.id.menu_sair_do_sistema:
                startActivity(new Intent(PainelDeControleMotoristaCobradorActivity.this, TelaPrincipalActivity.class));
                PainelDeControleMotoristaCobradorActivity.this.finish();
                break;
             default:
                 break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void irParaTelaListaDeAlunos(View view){
        startActivity(new Intent(this, ListaDeAlunosActivity.class));
        PainelDeControleMotoristaCobradorActivity.this.finish();
    }

    public void irParaTelaReceberPagamentos(View view){
        startActivity(new Intent(this, ListarPagamentos.class));
        PainelDeControleMotoristaCobradorActivity.this.finish();
    }

    public void irParaTelaRelatorios(View view){
        startActivity(new Intent(this, RelatoriosActivity.class));
        PainelDeControleMotoristaCobradorActivity.this.finish();
    }

}
