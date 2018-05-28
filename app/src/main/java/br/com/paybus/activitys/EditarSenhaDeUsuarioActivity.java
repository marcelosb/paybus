package br.com.paybus.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import br.com.paybus.utilitarios.PainelDeDialogo;
import br.com.paybus.utilitarios.Usuario;

public class EditarSenhaDeUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_alterar_senha);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(Usuario.tipo.equals("aluno")){
                    EditarSenhaDeUsuarioActivity.this.finish();
                    startActivity(new Intent(EditarSenhaDeUsuarioActivity.this, PainelDeControleAlunoActivity.class));
                }else if( Usuario.tipo.equals("cobrador") || Usuario.tipo.equals("motorista") ){
                    EditarSenhaDeUsuarioActivity.this.finish();
                    startActivity(new Intent(EditarSenhaDeUsuarioActivity.this, PainelDeControleMotoristaCobradorActivity.class));
                }else{
                    EditarSenhaDeUsuarioActivity.this.finish();
                    startActivity(new Intent(EditarSenhaDeUsuarioActivity.this, PainelDeControleAdminActivity.class));
                }

                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void alterarSenhaDoUsuario(View view) {

        EditText campoSenha = findViewById(R.id.campoSenhaAtual);
        EditText campoNova = findViewById(R.id.campoNovaSenha);
        EditText campoConfirmarNova = findViewById(R.id.campoConfirmarNovaSenha);


        if(Usuario.tipo.equals("aluno")){

            String senhaAtual = campoSenha.getText().toString();
            String novaSenha = campoNova.getText().toString();
            String confirmarNovaSenha = campoConfirmarNova.getText().toString();

            if( novaSenha.equals(confirmarNovaSenha)  ){
                AlunoDAO alunoDAO = new AlunoDAO(this);
                alunoDAO.atualizarSenhaDoAluno(Usuario.id, novaSenha);

                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(this);
                caixaDeDialogo.setCancelable(false);
                caixaDeDialogo.setTitle("Mensagem");
                caixaDeDialogo.setMessage("Senha alterada com sucesso!");
                caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        EditarSenhaDeUsuarioActivity.this.finish();
                        startActivity(new Intent(EditarSenhaDeUsuarioActivity.this, PainelDeControleAlunoActivity.class));
                    }
                });
                caixaDeDialogo.create();
                caixaDeDialogo.show();


            }else{
                PainelDeDialogo painelDeDialogo = new PainelDeDialogo();
                painelDeDialogo.mostrarMensagemDeErro("Erro","Verifique se a senha atual foi inserida corretamente no campo específicado\n\n" +
                        "Também verifique se a nova senha e a confirmação dessa nova senha foram digitadas corretamente", this );
            }

        }
        else if(Usuario.tipo.equals("cobrador")){

            String senhaAtual = campoSenha.getText().toString();
            String novaSenha = campoNova.getText().toString();
            String confirmarNovaSenha = campoConfirmarNova.getText().toString();

            if( novaSenha.equals(confirmarNovaSenha)  ){
                CobradorDAO cobradorDAO = new CobradorDAO(this);
                cobradorDAO.atualizarSenhaDoCobrador(Usuario.id, novaSenha);

                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(this);
                caixaDeDialogo.setCancelable(false);
                caixaDeDialogo.setTitle("Mensagem");
                caixaDeDialogo.setMessage("Senha alterada com sucesso!");
                caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        EditarSenhaDeUsuarioActivity.this.finish();
                        startActivity(new Intent(EditarSenhaDeUsuarioActivity.this, PainelDeControleMotoristaCobradorActivity.class));
                    }
                });
                caixaDeDialogo.create();
                caixaDeDialogo.show();


            }else{
                PainelDeDialogo painelDeDialogo = new PainelDeDialogo();
                painelDeDialogo.mostrarMensagemDeErro("Erro","Verifique se a senha atual foi inserida corretamente no campo específicado\n\n" +
                        "Também verifique se a nova senha e a confirmação dessa nova senha foram digitadas corretamente", this );
            }


        }else if(Usuario.tipo.equals("motorista")){

            String senhaAtual = campoSenha.getText().toString();
            String novaSenha = campoNova.getText().toString();
            String confirmarNovaSenha = campoConfirmarNova.getText().toString();

            if( novaSenha.equals(confirmarNovaSenha)  ){
                MotoristaDAO motoristaDAO = new MotoristaDAO(this);
                motoristaDAO.atualizarSenhaDoMotorista(Usuario.id, novaSenha);

                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(this);
                caixaDeDialogo.setCancelable(false);
                caixaDeDialogo.setTitle("Mensagem");
                caixaDeDialogo.setMessage("Senha alterada com sucesso!");
                caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        EditarSenhaDeUsuarioActivity.this.finish();
                        startActivity(new Intent(EditarSenhaDeUsuarioActivity.this, PainelDeControleMotoristaCobradorActivity.class));
                    }
                });
                caixaDeDialogo.create();
                caixaDeDialogo.show();


            }else{
                PainelDeDialogo painelDeDialogo = new PainelDeDialogo();
                painelDeDialogo.mostrarMensagemDeErro("Erro","Verifique se a senha atual foi inserida corretamente no campo específicado\n\n" +
                        "Também verifique se a nova senha e a confirmação dessa nova senha foram digitadas corretamente", this );
            }

        }
    }
}
