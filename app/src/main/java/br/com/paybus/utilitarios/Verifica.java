package br.com.paybus.utilitarios;

import android.content.Context;

import br.com.paybus.activitys.TelaPrincipalActivity;

public class Verifica {

    public static boolean login(String cpf, String senha, Context contexto){

        if(cpf.isEmpty() && senha.isEmpty()){
            PainelDeDialogo.mostrarMensagem("Campos em branco!", "Por favor, insira seu CPF e a sua Senha nos campos desejados",contexto);
            return false;
        } else if(cpf.isEmpty()){
            PainelDeDialogo.mostrarMensagem("Campo em branco", "Por favor, insira seu CPF", contexto);
            return false;
        }else if(senha.isEmpty()){
            PainelDeDialogo.mostrarMensagem("Campo em branco", "Por favor, insira sua Senha", contexto);
            return false;
        }else{
            return true;
        }

    }
}
