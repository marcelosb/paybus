package br.com.paybus.utilitarios;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import br.com.paybus.R;
import br.com.paybus.activitys.TelaPrincipalActivity;

public class PainelDeDialogo {

    public static void mostrarMensagem(String titulo, String mensagem, Context contexto){
            AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(contexto);
            caixaDeDialogo.setCancelable(false);
            caixaDeDialogo.setTitle(titulo);
            caixaDeDialogo.setMessage(mensagem);
            caixaDeDialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {@Override public void onClick(DialogInterface dialogInterface, int i) { } });
            caixaDeDialogo.show();
        }

}
