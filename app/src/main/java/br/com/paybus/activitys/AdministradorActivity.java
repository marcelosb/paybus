package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.paybus.R;

public class AdministradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_administrador);
    }

    public void adminIrParaPainelDeControle(View view){
        startActivity(new Intent(AdministradorActivity.this, PainelDeControleActivity.class) );
    }

    public void adminIrParaCadastrarUsuarios(View view){
        startActivity(new Intent(AdministradorActivity.this, CadastrarUsuariosActivity.class) );
    }
    
}
