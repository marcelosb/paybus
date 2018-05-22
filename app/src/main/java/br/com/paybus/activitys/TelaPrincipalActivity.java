package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.paybus.R;
import br.com.paybus.utilitarios.Verifica;

public class TelaPrincipalActivity extends AppCompatActivity {

    Verifica verifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
    }

    public void irParaPainelDeControle(View view){

        EditText campoCpf = findViewById(R.id.campoCPF);
        String campoCPF = campoCpf.getText().toString();

        EditText campoEditarSenha = findViewById(R.id.campoSenha);
        String campoSenha = campoEditarSenha.getText().toString();

        verifica = new Verifica();
        if(verifica.login(campoCPF, campoSenha, this) == true){
            if((campoCPF.equals("admin") || campoCPF.equals("ADMIN")) && (campoSenha.equals("admin") || campoSenha.equals("ADMIN"))  ){
                startActivity(new Intent(this, PainelDeControleAdminActivity.class ));
            }
            else if( (campoCPF.equals("motorista") || campoCPF.equals("MOTORISTA")) && (campoSenha.equals("123")) ){
                startActivity(new Intent(this, PainelDeControleMotoristaCobradorActivity.class ));
            }
            else if( (campoCPF.equals("cobrador") || campoCPF.equals("COBRADOR")) && (campoSenha.equals("123")) ){
                startActivity(new Intent(this, PainelDeControleMotoristaCobradorActivity.class ));
            }
            else if( (campoCPF.equals("aluno") || campoCPF.equals("ALUNO")) && (campoSenha.equals("123")) ){
                startActivity(new Intent(this, PainelDeControleAlunoActivity.class ));
            }
            else{
                startActivity(new Intent(this, PainelDeControleMotoristaCobradorActivity.class ));
            }
        }else{

        }


    }



}
