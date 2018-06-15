package br.com.paybus.utilitarios;

import android.content.Context;


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


    public boolean aluno(String nome, String instituicao, String cpf, String email, Context contexto){
        painelDeDialogo = new PainelDeDialogo();

        if(nome.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira seu nome e sobrenome", contexto);
            return false;
        }else if(instituicao.equals("Selecione a Instituição")){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, selecione sua instituição de ensino", contexto);
            return false;
        }else if(cpf.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira um número de CPF", contexto);
            return false;
        }else if(email.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira um endereço de email", contexto);
            return false;
        }
        else{
            return true;
        }

    }



    public boolean cobrador(String nome, String instituicao, String cpf, String email, Context contexto){
        painelDeDialogo = new PainelDeDialogo();

        if(nome.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira o nome e sobrenome do cobrador", contexto);
            return false;
        }else if(instituicao.equals("Selecione a Instituição")){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, selecione a instituição de ensino do cobrador", contexto);
            return false;
        }else if(cpf.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira um número de CPF", contexto);
            return false;
        }else if(email.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira um endereço de email", contexto);
            return false;
        }else{
            return true;
        }

    }


    public boolean motorista(String nome, String cpf, String email, Context contexto){
        painelDeDialogo = new PainelDeDialogo();

        if(nome.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira o nome e o sobrenome do motorista", contexto);
            return false;
        } else if(cpf.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira um número de CPF", contexto);
            return false;
        } else if(email.isEmpty()){
            painelDeDialogo.mostrarMensagemDeErro("Campo em branco", "Por favor, insira um endereço de email", contexto);
            return false;
        }else{
            return true;
        }

    }


}
