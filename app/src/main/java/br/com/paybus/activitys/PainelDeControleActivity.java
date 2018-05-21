package br.com.paybus.activitys;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.paybus.R;

public class PainelDeControleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle);

        ImageButton botaoNovoUsuario = findViewById(R.id.botaoNovoUsuario);
        TextView textoNovoUsuario = findViewById(R.id.textoNovoUsuario);
        ImageButton botaoRelatorios = findViewById(R.id.botaoRelatorios);
        TextView textoRelatorios = findViewById(R.id.textoRelatorios);
        ImageButton botaoInadimplentes = findViewById(R.id.botaoInadimplentes);
        TextView textoInadimplentes = findViewById(R.id.textoInadimplentes);
        ImageButton botaoReceberPagamentos = findViewById(R.id.botaoReceberPagamentos);
        TextView textoPagamentos = findViewById(R.id.textoPagamentos);

        if(TelaPrincipalActivity.acesso == "cobrador" || TelaPrincipalActivity.acesso == "motorista"){
            botaoNovoUsuario.setVisibility(ImageButton.INVISIBLE);
            textoNovoUsuario.setVisibility(TextView.INVISIBLE);
            botaoRelatorios.setVisibility(ImageButton.INVISIBLE);
            textoRelatorios.setVisibility(TextView.INVISIBLE);
            botaoInadimplentes.setVisibility(ImageButton.VISIBLE);
            textoInadimplentes.setVisibility(TextView.VISIBLE);
            botaoReceberPagamentos.setVisibility(ImageButton.VISIBLE);
            textoPagamentos.setVisibility(TextView.VISIBLE);
        }

        else if(TelaPrincipalActivity.acesso == "admin"){
            botaoNovoUsuario.setVisibility(ImageButton.VISIBLE);
            textoNovoUsuario.setVisibility(TextView.VISIBLE);
            botaoRelatorios.setVisibility(ImageButton.VISIBLE);
            textoRelatorios.setVisibility(TextView.VISIBLE);
            botaoInadimplentes.setVisibility(ImageButton.VISIBLE);
            textoInadimplentes.setVisibility(TextView.VISIBLE);
            botaoReceberPagamentos.setVisibility(ImageButton.VISIBLE);
            textoPagamentos.setVisibility(TextView.VISIBLE);
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
            case R.id.menu_sair_do_sistema:
                finish();
                break;
            case R.id.menu_sobre:
                startActivity(new Intent(PainelDeControleActivity.this, SobreActivity.class));
                break;
             default:
                 break;
        }

        return super.onOptionsItemSelected(item);
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

    public void irParaTelaNovoUsuario(View view){
        startActivity(new Intent(this, CadastrarUsuariosActivity.class));
    }

    public void irParaTelaRelatorios(View view){
        startActivity(new Intent(this, RelatoriosActivity.class));
    }

}
