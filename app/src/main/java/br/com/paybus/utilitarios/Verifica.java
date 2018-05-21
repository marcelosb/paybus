package br.com.paybus.utilitarios;

import android.content.Context;

import br.com.paybus.activitys.TelaPrincipalActivity;

public class Verifica {

    PainelDeDialogo painelDeDialogo;


    public boolean login(String cpf, String senha, Context contexto){
        painelDeDialogo = new PainelDeDialogo();

        if(cpf.isEmpty() && senha.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campos em branco!", "Por favor, insira seu CPF e a sua Senha nos campos desejados",contexto);
            return false;
        } else if(cpf.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira seu CPF", contexto);
            return false;
        }else if(senha.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira sua Senha", contexto);
            return false;
        }else{
            return true;
        }

    }
}
