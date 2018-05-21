package br.com.paybus.acesso_banco_de_dados;

public class Coluna {

    public static final String ID = "id";
    public static final String NOME_COMPLETO = "nome_completo";
    public static final String INSTITUICAO = "instituicao";
    public static final String CPF = "cpf";
    public static final String ENDERECO = "endereco";
    public static final String TELEFONE = "telefone";
    public static final String SENHA = "senha";
    public static final String TIPO_DE_USUARIO = "tipo_de_usuario";

    // ESSA COLUNA PERTENCE A TABELA MOTORISTA
    public static final String CNH_MOTORISTA = "cnh";

    // ESSAS COLUNAS PERTENCEM A TABELA PAGAMENTOS
    public static final String NOME_DO_ALUNO_PAGADOR = "nome_do_aluno_pagante";
    public static final String NOME_DO_COBRADOR = "nome_do_cobrador";
    public static final String NOME_DO_MOTORISTA = "nome_do_motorista";
    public static final String DATA_DO_PAGAMENTO = "data_do_pagamento";
    public static final String DATA_DO_VENCIMENTO = "data_do_vencimento";
    public static final String VALOR_DO_PAGAMENTO = "valor";
    public static final String STATUS = "status";
    public static final String OBSERVACAO = "observacao";


}
