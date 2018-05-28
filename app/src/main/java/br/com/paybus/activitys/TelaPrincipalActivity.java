package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.modelo.Motorista;
import br.com.paybus.utilitarios.PainelDeDialogo;
import br.com.paybus.utilitarios.Usuario;
import br.com.paybus.utilitarios.Verifica;

public class TelaPrincipalActivity extends AppCompatActivity {

    Verifica verifica;
    AlunoDAO alunoDAO;
    CobradorDAO cobradorDAO;
    MotoristaDAO motoristaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                TelaPrincipalActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void irParaPainelDeControle(View view){

        alunoDAO = new AlunoDAO(this);
        cobradorDAO = new CobradorDAO(this);
        motoristaDAO = new MotoristaDAO(this);

        EditText campo_email = findViewById(R.id.campoEmailLogin);
        String campoEmail = campo_email.getText().toString();

        EditText campoEditarSenha = findViewById(R.id.campoSenhaLogin);
        String campoSenha = campoEditarSenha.getText().toString();

        verifica = new Verifica();
        if(verifica.login(campoEmail, campoSenha, this)){
            if((campoEmail.equals("admin") || campoEmail.equals("ADMIN")) && (campoSenha.equals("admin") || campoSenha.equals("123"))  ){
                Usuario.nome = "Admin do sistema";
                TelaPrincipalActivity.this.finish();
                startActivity(new Intent(this, PainelDeControleAdminActivity.class));
            }
            else if( cobradorDAO.fazerLogin(campoEmail, campoSenha) ){
                cobradorDAO = new CobradorDAO(this);
                List<Cobrador> listaDeCobradores = cobradorDAO.listarCobradores();
                for(int i=0; i< listaDeCobradores.size(); i++){
                    if( (listaDeCobradores.get(i).getEmail().equals(campoEmail)) && (listaDeCobradores.get(i).getSenha().equals(campoSenha)) ){
                        Usuario.id = listaDeCobradores.get(i).getId();
                        Usuario.nome = listaDeCobradores.get(i).getNomeCompleto();
                        Usuario.senha = listaDeCobradores.get(i).getSenha();
                        Usuario.tipo = listaDeCobradores.get(i).getTipoDeUsuario();
                        TelaPrincipalActivity.this.finish();
                        startActivity(new Intent(this, PainelDeControleMotoristaCobradorActivity.class));
                        return;
                    }
                }
            }
            else if( alunoDAO.fazerLogin(campoEmail, campoSenha) ){
                alunoDAO = new AlunoDAO(this);
                List<Aluno> listaDeAlunos = alunoDAO.listarAlunos();
                for(int i=0; i< listaDeAlunos.size(); i++) {
                    if( (listaDeAlunos.get(i).getEmail().equals(campoEmail)) && (listaDeAlunos.get(i).getSenha().equals(campoSenha)) ) {
                        Usuario.id = listaDeAlunos.get(i).getId();
                        Usuario.nome = listaDeAlunos.get(i).getNomeCompleto();
                        Usuario.senha = listaDeAlunos.get(i).getSenha();
                        Usuario.tipo = listaDeAlunos.get(i).getTipoDeUsuario();
                        TelaPrincipalActivity.this.finish();
                        startActivity(new Intent(this, PainelDeControleAlunoActivity.class));
                        return;
                    }
                }
            }
            else if( motoristaDAO.fazerLogin(campoEmail, campoSenha) ){
                motoristaDAO = new MotoristaDAO(this);
                List<Motorista> listaDeMotoristas = motoristaDAO.listarMotoristas();
                for(int i=0; i< listaDeMotoristas.size(); i++) {
                    if( (listaDeMotoristas.get(i).getEmail().equals(campoEmail)) && (listaDeMotoristas.get(i).getSenha().equals(campoSenha)) ) {
                        Usuario.id = listaDeMotoristas.get(i).getId();
                        Usuario.nome = listaDeMotoristas.get(i).getNomeCompleto();
                        Usuario.senha = listaDeMotoristas.get(i).getSenha();
                        Usuario.tipo = listaDeMotoristas.get(i).getTipoDeUsuario();
                        TelaPrincipalActivity.this.finish();
                        startActivity(new Intent(this, PainelDeControleMotoristaCobradorActivity.class));
                        return;
                    }
                }
            }
            else{
                new PainelDeDialogo().mostrarMensagemDeErro("Erro","Email ou Senha incorreta, tente novamente." , this);
            }

        }


    }



}
