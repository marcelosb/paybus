package br.com.paybus.acesso_banco_de_dados;

public class Tabela {

    public static final String ALUNO = "tabela_aluno";
    public static final String COBRADOR = "tabela_cobrador";
    public static final String MOTORISTA = "tabela_motorista";

    public static String aluno(){
        return "CREATE TABLE "+ALUNO+" ("+
                Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Coluna.NOME_COMPLETO+ " TEX NOT NULL, "+
                Coluna.INSTITUICAO+" TEXT NOT NULL, "+
                Coluna.CPF+" TEXT, "+
                Coluna.ENDERECO+" TEXT, "+
                Coluna.TELEFONE+" TEXT, "+
                Coluna.SENHA+" TEXT, "+
                Coluna.TIPO_DE_USUARIO+" TEXT);";
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
                Coluna.TIPO_DE_USUARIO+" TEXT);";
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
                Coluna.TIPO_DE_USUARIO+" TEXT);";
    }




    /**
     *
     public static String pagamento(){
     return "CREATE TABLE "+TABELA+"pagamento"+" ("+
     Coluna.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
     Coluna.NOME_COMPLETO+ " TEX NOT NULL, "+
     Coluna.CPF+" TEXT, "+
     Coluna.ENDERECO+" TEXT, "+
     Coluna.CNH_MOTORISTA+" TEXT NOT NULL, "+
     Coluna.TELEFONE+" TEXT, "+
     Coluna.SENHA+" TEXT);";
     }
     *
     private static final String PAGAMENTOS = "pagamentos";
     private static final String ID_PAGAMENTOS = "id_pagamento";
     private static final String DATA_VENCIMENTO = "data_vencimento";
     private static final String DATA_PAGAMENTO = "data_pagamento";
     private static final String VALOR_PAGAMENTO = "valor_pagamento";
     private static final String STATUS = "status";
     private static final String OBSERVACAO = "observacao";

     private static final String RELATORIO_MENSAL = "relatorio_mensal";
     private static final String ID_RELATORIO_MENSAL = "id_relatorio_mensal";
     private static final String DATA_RELATORIO = "data_relatorio";
     private static final String ESTUDANTES_PAGOS = "estudantes_pagos";
     private static final String ESTUDANTES_INADIPLENTES = "estudantes_inadiplentes";
     private static final String TOTAL_PAGO_MENSAL = "total_mensal_pago";
     private static final String DEBITO_MENSAL = "debito_mensal";
     private static final String VALOR_TOTAL_MENSAL = "valor_total_mensal";
     private static final String OBSERVACAO_RELATORIO_MENSAL = "observacao";
     *
     String query_pagamentos = "CREATE TABLE "+PAGAMENTOS+" ("+ID_PAGAMENTOS+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
     DATA_PAGAMENTO+" TEXT, "+VALOR_PAGAMENTO+" DOUBLE, "+
     DATA_VENCIMENTO+" TEXT, "+STATUS+" TEXT, "+ID_ALUNO+" INTEGER, "+ID_MOTORISTA+" INTEGER, "+
     ID_COBRADOR+" INTEGER, "+OBSERVACAO+" TEXT, FOREIGN KEY("+ID_ALUNO+")REFERENCES "+ALUNO+" ("+ID_ALUNO+" ), " +
     "FOREIGN KEY("+ID_MOTORISTA+")REFERENCES "+MOTORISTA+" ("+ID_MOTORISTA+"), FOREIGN KEY("+ID_COBRADOR+")" +
     "REFERENCES "+COBRADOR+" ("+ID_COBRADOR+"));";
     db.execSQL(query_pagamentos);



     String query_relatorio="CREATE TABLE "+RELATORIO_MENSAL+" ("+ID_RELATORIO_MENSAL+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DATA_RELATORIO+" TEXT, "+
     ESTUDANTES_PAGOS+" INTEGER, "+ESTUDANTES_INADIPLENTES+" INTEGER, "+TOTAL_PAGO_MENSAL+" DOUBLE, "+DEBITO_MENSAL+" DOUBLE, "+VALOR_TOTAL_MENSAL+" DOUBLE, "+
     OBSERVACAO_RELATORIO_MENSAL+" TEXT);";
     db.execSQL(query_relatorio);
     */


}



