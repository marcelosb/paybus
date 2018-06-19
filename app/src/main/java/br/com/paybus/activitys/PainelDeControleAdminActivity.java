package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.paybus.R;
import br.com.paybus.utilitarios.Usuario;

public class PainelDeControleAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle_admin);

        TextView textoNomeAdminlogado = findViewById(R.id.textoNomeAdminLogado);
        textoNomeAdminlogado.setText("Olá "+ Usuario.nome+", seja bem vindo!");
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
                startActivity(new Intent(PainelDeControleAdminActivity.this, ViewPDFAjudaActivity.class));
                PainelDeControleAdminActivity.this.finish();
                break;
            case R.id.menu_alterar_senha:
                startActivity(new Intent(PainelDeControleAdminActivity.this, EditarSenhaDeUsuarioActivity.class));
                PainelDeControleAdminActivity.this.finish();
                break;
            case R.id.menu_sair_do_sistema:
                startActivity(new Intent(PainelDeControleAdminActivity.this, TelaPrincipalActivity.class));
                PainelDeControleAdminActivity.this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PainelDeControleAdminActivity.this, TelaPrincipalActivity.class));
        PainelDeControleAdminActivity.this.finish();
    }

    public void irParaTelaUsuarios(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, UsuariosActivity.class));
        PainelDeControleAdminActivity.this.finish();
    }

    public void irParaTelaReceberPagamentos(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, ListarPagamentos.class));
        PainelDeControleAdminActivity.this.finish();
    }

    public void irParaTelaRelatorios(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, RelatoriosActivity.class));
        PainelDeControleAdminActivity.this.finish();
    }

    public void irParaTelaFinanceira(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, ParteFinanceiraActivity.class));
        PainelDeControleAdminActivity.this.finish();
    }

}
