package br.com.paybus.utilitarios;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.paybus.R;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.activitys.TelaPrincipalActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;


public class PainelDeDialogo extends Activity{


    public void mostrarMensagemDeErro(String titulo, String mensagem, Context contexto){
        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(contexto);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle(titulo);
        caixaDeDialogo.setMessage(mensagem);
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        caixaDeDialogo.show();
    }


    public void mostrarMensagem(String titulo, String mensagem, final Context contexto){
        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(contexto);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle(titulo);
        caixaDeDialogo.setMessage(mensagem);
        caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        caixaDeDialogo.create();
        caixaDeDialogo.show();
    }


    public int controlador = 0;

    public boolean mostrarMensagemDeConfirmacao(String titulo, String mensagem, Context contexto){

        final AlunoDAO dao = new AlunoDAO(contexto);
        final List<Aluno> listaAtualizadaAluno;

        final Context context = contexto;


        AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
        caixaDeDialogo.setCancelable(false);
        caixaDeDialogo.setTitle(titulo);
        caixaDeDialogo.setMessage(mensagem);

        caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                controlador = 0;
            }
        });

        caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                controlador = 1;
            }
        });

        caixaDeDialogo.create();
        caixaDeDialogo.show();

        if(controlador == 1)
            return true;
        else
            return false;

    }

}
