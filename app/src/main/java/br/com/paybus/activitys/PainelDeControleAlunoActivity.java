package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.paybus.R;
import br.com.paybus.utilitarios.Usuario;

public class PainelDeControleAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle_aluno);

        TextView textoNomeAlunologado = findViewById(R.id.textoNomeAlunoLogado);
        textoNomeAlunologado.setText("Olá "+Usuario.nome+"\nVocê está logado(a) como "+Usuario.tipo+"(a)");
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
                PainelDeControleAlunoActivity.this.finish();
                startActivity(new Intent(PainelDeControleAlunoActivity.this, SobreActivity.class));
                break;
            case R.id.menu_alterar_senha:
                PainelDeControleAlunoActivity.this.finish();
                startActivity(new Intent(this, EditarSenhaDeUsuarioActivity.class));
                break;
            case R.id.menu_sair_do_sistema:
                PainelDeControleAlunoActivity.this.finish();
                startActivity(new Intent(this, TelaPrincipalActivity.class));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
