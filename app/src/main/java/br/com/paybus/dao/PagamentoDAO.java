package br.com.paybus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.acesso_banco_de_dados.Coluna;
import br.com.paybus.acesso_banco_de_dados.ConexaoBD;
import br.com.paybus.acesso_banco_de_dados.Tabela;
import br.com.paybus.activitys.ListaDeAlunosPagamentos;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamento;

public class PagamentoDAO {

    public SQLiteDatabase db;

    public PagamentoDAO(Context context) {
        ConexaoBD conexao = new ConexaoBD(context);
        db = conexao.getWritableDatabase();
    }

    public void inserirPagamento(Pagamento pagamento, List<Aluno> listaDeAlunos){
        ContentValues values = new ContentValues();
        try{
            for(int i=0; i<listaDeAlunos.size(); i++){
                values.put(Coluna.MES_E_ANO_DO_PAGAMENTO, pagamento.getMesEAnoDoPagamento());
                values.put(Coluna.DATA_DO_PAGAMENTO, pagamento.getDataDoPagamento());
                values.put(Coluna.DATA_DO_VENCIMENTO, pagamento.getDataDoVencimento());
                values.put(Coluna.VALOR_DO_PAGAMENTO, pagamento.getValorDoPagamento());
                values.put(Coluna.STATUS, pagamento.getStatus());
                values.put(Coluna.NOME_DO_ALUNO, listaDeAlunos.get(i).getNomeCompleto());
                values.put(Coluna.NOME_DA_INSTITUICAO_DE_ENSINO_DO_ALUNO, listaDeAlunos.get(i).getInstituicao());
                values.put(Coluna.NOME_DO_COBRADOR, pagamento.getNomeDoCobrador());
                values.put(Coluna.NOME_DO_MOTORISTA, pagamento.getNomeDoMotorista());
                values.put(Coluna.OBSERVACAO, pagamento.getObservacao());

                db.insert(Tabela.PAGAMENTO,null, values);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void inserirPagamento(Pagamento pagamento){
        ContentValues values = new ContentValues();
        try{
            values.put(Coluna.MES_E_ANO_DO_PAGAMENTO, pagamento.getMesEAnoDoPagamento());
            values.put(Coluna.DATA_DO_PAGAMENTO, pagamento.getDataDoPagamento());
            values.put(Coluna.DATA_DO_VENCIMENTO, pagamento.getDataDoVencimento());
            values.put(Coluna.VALOR_DO_PAGAMENTO, pagamento.getValorDoPagamento());
            values.put(Coluna.STATUS, pagamento.getStatus());
            values.put(Coluna.NOME_DO_ALUNO, pagamento.getNomeDoAluno());
            values.put(Coluna.NOME_DA_INSTITUICAO_DE_ENSINO_DO_ALUNO, pagamento.getInstituicaoDeEnsinoDoAluno());
            values.put(Coluna.NOME_DO_COBRADOR, pagamento.getNomeDoCobrador());
            values.put(Coluna.NOME_DO_MOTORISTA, pagamento.getNomeDoMotorista());
            values.put(Coluna.OBSERVACAO, pagamento.getObservacao());

            db.insert(Tabela.PAGAMENTO,null, values);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }


    public void atualizarPagamento(Pagamento pagamento){
        ContentValues values = new ContentValues();
        try{
            values.put(Coluna.MES_E_ANO_DO_PAGAMENTO, pagamento.getMesEAnoDoPagamento());
            values.put(Coluna.DATA_DO_PAGAMENTO, pagamento.getDataDoPagamento());
            values.put(Coluna.DATA_DO_VENCIMENTO, pagamento.getDataDoVencimento());
            values.put(Coluna.VALOR_DO_PAGAMENTO, pagamento.getValorDoPagamento());
            values.put(Coluna.STATUS, pagamento.getStatus());
            values.put(Coluna.NOME_DO_ALUNO, pagamento.getNomeDoAluno());
            values.put(Coluna.NOME_DA_INSTITUICAO_DE_ENSINO_DO_ALUNO, pagamento.getInstituicaoDeEnsinoDoAluno());
            values.put(Coluna.NOME_DO_COBRADOR, pagamento.getNomeDoCobrador());
            values.put(Coluna.NOME_DO_MOTORISTA, pagamento.getNomeDoMotorista());
            values.put(Coluna.OBSERVACAO, pagamento.getObservacao());

            db.update(Tabela.PAGAMENTO, values,Coluna.ID + "= ?",new String[]{String.valueOf(pagamento.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void atualizarPagamentoMes(List<Pagamento> list){
        ContentValues values = new ContentValues();
        //List<Pagamento> lista;
        try{
            for(int i=0; i<list.size(); i++){
                values.put(Coluna.MES_E_ANO_DO_PAGAMENTO, list.get(i).getMesEAnoDoPagamento());
                values.put(Coluna.DATA_DO_PAGAMENTO, list.get(i).getDataDoPagamento());
                values.put(Coluna.DATA_DO_VENCIMENTO, list.get(i).getDataDoVencimento());
                values.put(Coluna.VALOR_DO_PAGAMENTO, list.get(i).getValorDoPagamento());
                values.put(Coluna.STATUS, list.get(i).getStatus());
                values.put(Coluna.NOME_DO_ALUNO, list.get(i).getNomeDoAluno());
                values.put(Coluna.NOME_DA_INSTITUICAO_DE_ENSINO_DO_ALUNO, list.get(i).getInstituicaoDeEnsinoDoAluno());
                values.put(Coluna.NOME_DO_COBRADOR, list.get(i).getNomeDoCobrador());
                values.put(Coluna.NOME_DO_MOTORISTA, list.get(i).getNomeDoMotorista());
                values.put(Coluna.OBSERVACAO, list.get(i).getObservacao());

                //db.insert(Tabela.PAGAMENTO,null, values);
                db.update(Tabela.PAGAMENTO, values,Coluna.ID + "= ?",new String[]{String.valueOf(list.get(i).getId())});
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

    }


    public List<Pagamento> listarPagamentosDosAlunos(String mesDePagamento){
        List<Pagamento> listaPagamentosAlunos = new ArrayList<Pagamento>();
        try {
            Cursor cursor = db.rawQuery( "SELECT * FROM "+Tabela.PAGAMENTO +" WHERE mes_e_ano_do_pagamento = ? ", new String[]{mesDePagamento});
            //String queryListaPagamentoAluno = "SELECT * FROM "+Tabela.PAGAMENTO+";";
            //Cursor cursor = db.rawQuery(queryListaPagamentoAluno, null);
            ListaDeAlunosPagamentos.totalPagadoresEDevedores = 0;
            if (cursor.moveToFirst()) {
                do {
                    Pagamento pagamento = new Pagamento();
                    pagamento.setId(Integer.parseInt(cursor.getString(0)));
                    pagamento.setMesEAnoDoPagamento(cursor.getString(1));
                    pagamento.setDataDoPagamento(cursor.getString(2));
                    pagamento.setDataDoVencimento(cursor.getString(3));
                    pagamento.setValorDoPagamento(Double.parseDouble(cursor.getString(4)));
                    pagamento.setStatus(cursor.getString(5));
                    pagamento.setNomeDoAluno(cursor.getString(6));
                    pagamento.setInstituicaoDeEnsinoDoAluno(cursor.getString(7));
                    pagamento.setNomeDoCobrador(cursor.getString(8));
                    pagamento.setNomeDoMotorista(cursor.getString(9));
                    pagamento.setObservacao(cursor.getString(10));

                    listaPagamentosAlunos.add(pagamento);

                    ListaDeAlunosPagamentos.totalPagadoresEDevedores = ListaDeAlunosPagamentos.totalPagadoresEDevedores + 1;
                } while (cursor.moveToNext());
            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaPagamentosAlunos;
    }

    public List<Pagamento> listarPagamentosPagadoresEDevedores(String mesDePagamento, String status){
        List<Pagamento> listaPagamentosAlunosDevedores = new ArrayList<Pagamento>();
        try {
            Cursor cursor = db.rawQuery( "SELECT * FROM "+Tabela.PAGAMENTO +" WHERE mes_e_ano_do_pagamento = ? AND status = ? ", new String[]{mesDePagamento, status});
            //String queryListaPagamentoAluno = "SELECT * FROM "+Tabela.PAGAMENTO+";";
            //Cursor cursor = db.rawQuery(queryListaPagamentoAluno, null);
            int contador = 0;
            if (cursor.moveToFirst()) {
                do {
                    Pagamento pagamento = new Pagamento();
                    pagamento.setId(Integer.parseInt(cursor.getString(0)));
                    pagamento.setMesEAnoDoPagamento(cursor.getString(1));
                    pagamento.setDataDoPagamento(cursor.getString(2));
                    pagamento.setDataDoVencimento(cursor.getString(3));
                    pagamento.setValorDoPagamento(Double.parseDouble(cursor.getString(4)));
                    pagamento.setStatus(cursor.getString(5));
                    pagamento.setNomeDoAluno(cursor.getString(6));
                    pagamento.setInstituicaoDeEnsinoDoAluno(cursor.getString(7));
                    pagamento.setNomeDoCobrador(cursor.getString(8));
                    pagamento.setNomeDoMotorista(cursor.getString(9));
                    pagamento.setObservacao(cursor.getString(10));

                    listaPagamentosAlunosDevedores.add(pagamento);
                    contador = contador + 1;
                } while (cursor.moveToNext());

                if(status.equals("Pago")){
                    ListaDeAlunosPagamentos.numeroDePagadores = contador;
                }else if(status.equals("Não Pago")){
                    ListaDeAlunosPagamentos.numeroDeDevedores = contador;
                }

            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaPagamentosAlunosDevedores;
    }

    public List<Pagamento> listarPagamentosDosAlunosPorMes(String mes){
        Pagamento pagamento = null;
        List<Pagamento> listaPagamentosAlunosMes = new ArrayList<>();
        try {
            //String queryListaPagamentoAluno = "SELECT * FROM "+Tabela.PAGAMENTO+";";
            //Cursor cursor = db.rawQuery(queryListaPagamentoAluno, null);

            Cursor cursor = db.rawQuery( "SELECT * FROM "+Tabela.PAGAMENTO +" WHERE mes_e_ano_do_pagamento = ? ", new String[]{mes});

            // SELECT * FROM tabela_pagamento WHERE mes_e_ano_do_pagamento='mes';
            //String queryListaPagamentoAlunoMes = "SELECT * FROM "+Tabela.PAGAMENTO+" WHERE "+Coluna.MES_E_ANO_DO_PAGAMENTO+"='"+mes+"';";
            //Cursor cursor = db.rawQuery(queryListaPagamentoAlunoMes, null);
            if (cursor.moveToFirst()) {
                do {
                    pagamento = new Pagamento();
                    pagamento.setId(Integer.parseInt(cursor.getString(0)));
                    pagamento.setMesEAnoDoPagamento(cursor.getString(1));
                    pagamento.setDataDoPagamento(cursor.getString(2));
                    pagamento.setDataDoVencimento(cursor.getString(3));
                    pagamento.setValorDoPagamento(Double.parseDouble(cursor.getString(4)));
                    pagamento.setStatus(cursor.getString(5));
                    pagamento.setNomeDoAluno(cursor.getString(6));
                    pagamento.setInstituicaoDeEnsinoDoAluno(cursor.getString(7));
                    pagamento.setNomeDoCobrador(cursor.getString(8));
                    pagamento.setNomeDoMotorista(cursor.getString(9));
                    pagamento.setObservacao(cursor.getString(10));

                    listaPagamentosAlunosMes.add(pagamento);
                } while (cursor.moveToNext());
            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaPagamentosAlunosMes;
    }


    public void deletarPagamento(Pagamento pagamento){
        try {
            db.delete(Tabela.PAGAMENTO, Coluna.ID + "= ?", new String[]{String.valueOf(pagamento.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void deletarMesDePagamento(String mesDePagamento){
        try {
            db.delete(Tabela.PAGAMENTO, Coluna.MES_E_ANO_DO_PAGAMENTO + "= ?", new String[]{mesDePagamento});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

}
