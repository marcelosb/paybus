package br.com.paybus.acesso_banco_de_dados;

public class Tabela {

    public static final String ADMINISTRADOR = "tabela_administrador";
    public static final String ALUNO = "tabela_aluno";
    public static final String COBRADOR = "tabela_cobrador";
    public static final String MOTORISTA = "tabela_motorista";
    public static final String PAGAMENTO = "tabela_pagamento";
    public static final String MES_DO_PAGAMENTO = "tabela_mes_do_pagamento";

    public static String admninistrador(){
        return "CREATE TABLE "+ADMINISTRADOR+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY, "+
                Coluna.EMAIL+ " TEX NOT NULL, "+
                Coluna.SENHA+" TEXT, "+
                Coluna.TIPO_DE_USUARIO+" TEXT);";
    }

    public static String cadastrarAdmninistrador(){
        return "INSERT INTO tabela_administrador VALUES ('1', 'admin', 'admin', 'admin');";
    }

    public static String aluno(){
        return "CREATE TABLE "+ALUNO+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Coluna.NOME_COMPLETO+ " TEX NOT NULL, "+
                Coluna.INSTITUICAO+" TEXT NOT NULL, "+
                Coluna.CPF+" TEXT, "+
                Coluna.ENDERECO+" TEXT, "+
                Coluna.TELEFONE+" TEXT, "+
                Coluna.SENHA+" TEXT, "+
                Coluna.TIPO_DE_USUARIO+" TEXT, "+
                Coluna.EMAIL+" TEXT);";
    }

    public static String cobrador(){
        return "CREATE TABLE "+COBRADOR+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Coluna.NOME_COMPLETO+ " TEX NOT NULL, "+
                Coluna.INSTITUICAO+" TEXT NOT NULL, "+
                Coluna.CPF+" TEXT, "+
                Coluna.ENDERECO+" TEXT, "+
                Coluna.TELEFONE+" TEXT, "+
                Coluna.SENHA+" TEXT, "+
                Coluna.TIPO_DE_USUARIO+" TEXT, "+
                Coluna.EMAIL+" TEXT);";
    }

    public static String motorista(){
        return "CREATE TABLE "+MOTORISTA+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Coluna.NOME_COMPLETO+ " TEX NOT NULL, "+
                Coluna.CPF+" TEXT, "+
                Coluna.ENDERECO+" TEXT, "+
                Coluna.CNH_MOTORISTA+" TEXT NOT NULL, "+
                Coluna.TELEFONE+" TEXT, "+
                Coluna.SENHA+" TEXT, "+
                Coluna.TIPO_DE_USUARIO+" TEXT, "+
                Coluna.EMAIL+" TEXT);";
    }

    public static String pagamento(){
        return "CREATE TABLE "+PAGAMENTO+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Coluna.MES_E_ANO_DO_PAGAMENTO+ " TEXT, "+
                Coluna.DATA_DO_PAGAMENTO+ " TEXT, "+
                Coluna.DATA_DO_VENCIMENTO+" TEXT, "+
                Coluna.VALOR_DO_PAGAMENTO+" REAL, "+
                Coluna.STATUS+" TEXT, "+
                Coluna.NOME_DO_ALUNO+" TEXT, "+
                Coluna.NOME_DA_INSTITUICAO_DE_ENSINO_DO_ALUNO+" TEXT, "+
                Coluna.NOME_DO_COBRADOR+" TEXT, "+
                Coluna.NOME_DO_MOTORISTA+" TEXT, "+
                Coluna.OBSERVACAO+" TEXT);";
    }

    public static String mesDoPagamento(){
        return "CREATE TABLE "+MES_DO_PAGAMENTO+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Coluna.MES_E_ANO_DO_PAGAMENTO+ " TEXT, "+
                Coluna.DATA_DO_VENCIMENTO+" TEXT);";
    }
}



