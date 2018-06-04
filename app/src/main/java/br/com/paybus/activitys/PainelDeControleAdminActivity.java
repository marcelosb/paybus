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
        textoNomeAdminlogado.setText("Olá "+ Usuario.nome+"\nVocê está logado como Administrador");
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
                //PainelDeControleAdminActivity.this.finish();
                startActivity(new Intent(PainelDeControleAdminActivity.this, SobreActivity.class));
                break;
            case R.id.menu_alterar_senha:
                PainelDeControleAdminActivity.this.finish();
                startActivity(new Intent(PainelDeControleAdminActivity.this, EditarSenhaDeUsuarioActivity.class));
                break;
            case R.id.menu_sair_do_sistema:
                PainelDeControleAdminActivity.this.finish();
                startActivity(new Intent(PainelDeControleAdminActivity.this, TelaPrincipalActivity.class));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void irParaTelaUsuarios(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, UsuariosActivity.class));
    }

    public void irParaTelaReceberPagamentos(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, ListarPagamentos.class));
    }

    public void irParaTelaRelatorios(View view){
        startActivity(new Intent(PainelDeControleAdminActivity.this, RelatoriosActivity.class));
    }


}
