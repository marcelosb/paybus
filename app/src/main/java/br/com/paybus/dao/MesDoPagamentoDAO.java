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
import br.com.paybus.modelo.MesDoPagamento;

public class MesDoPagamentoDAO {

    public SQLiteDatabase db;

    public MesDoPagamentoDAO(Context context) {
        ConexaoBD conexao = new ConexaoBD(context);
        db = conexao.getWritableDatabase();
    }

    public void inserirMesDoPagamento(MesDoPagamento mesDoPagamento){
        ContentValues values = new ContentValues();
        try{
            values.put(Coluna.MES_E_ANO_DO_PAGAMENTO, mesDoPagamento.getMesEAnoDoPagamento());
            values.put(Coluna.DATA_DO_VENCIMENTO, mesDoPagamento.getDataDoVencimento());

            db.insert(Tabela.MES_DO_PAGAMENTO,null, values);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void atualizarMesDoPagamento(MesDoPagamento mesDoPagamento){
        ContentValues values = new ContentValues();
        try{
            values.put(Coluna.MES_E_ANO_DO_PAGAMENTO, mesDoPagamento.getMesEAnoDoPagamento());
            values.put(Coluna.DATA_DO_VENCIMENTO, mesDoPagamento.getDataDoVencimento());

            db.update(Tabela.MES_DO_PAGAMENTO, values,Coluna.ID + "= ?",new String[]{String.valueOf(mesDoPagamento.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void deletarMesDoPagamento(MesDoPagamento mesDoPagamento){
        try {
            db.delete(Tabela.MES_DO_PAGAMENTO, Coluna.ID + "= ?", new String[]{String.valueOf(mesDoPagamento.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }


    public List<MesDoPagamento> listarMesesDoPagamento(){
        List<MesDoPagamento> listaDeMesesDoPagamento = new ArrayList<MesDoPagamento>();
        try {
            String queryListaMesDePagamento = "SELECT * FROM "+Tabela.MES_DO_PAGAMENTO+";";
            Cursor cursor = db.rawQuery(queryListaMesDePagamento, null);
            if (cursor.moveToFirst()) {
                do {
                    MesDoPagamento mesDoPagamento = new MesDoPagamento();
                    mesDoPagamento.setId(Integer.parseInt(cursor.getString(0)));
                    mesDoPagamento.setMesEAnoDoPagamento(cursor.getString(1));
                    mesDoPagamento.setDataDoVencimento(cursor.getString(2));

                    listaDeMesesDoPagamento.add(mesDoPagamento);
                } while (cursor.moveToNext());
            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaDeMesesDoPagamento;
    }









}
