package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import br.com.paybus.R;
import br.com.paybus.utilitarios.Usuario;

public class UsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_usuarios);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(UsuariosActivity.this, PainelDeControleAdminActivity.class));
                UsuariosActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UsuariosActivity.this, PainelDeControleAdminActivity.class));
        UsuariosActivity.this.finish();
    }

    public void irParaCadastroDeMotorista(View view){
        startActivity(new Intent(UsuariosActivity.this, ListaDeMotoristasActivity.class));
        UsuariosActivity.this.finish();
    }

    public void irParaCadastroDeCobrador(View view){
        startActivity(new Intent(UsuariosActivity.this, ListaDeCobradoresActivity.class));
        UsuariosActivity.this.finish();
    }

    public void irParaCadastroDeAluno(View view){
        startActivity(new Intent(UsuariosActivity.this, ListaDeAlunosActivity.class));
        UsuariosActivity.this.finish();
    }

}
