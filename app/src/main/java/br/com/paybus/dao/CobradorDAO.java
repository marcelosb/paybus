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
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Cobrador;

public class CobradorDAO {

    private SQLiteDatabase db;

    public CobradorDAO(Context context) {
        ConexaoBD conexao = new ConexaoBD(context);
        db = conexao.getWritableDatabase();
    }

    public void inserirCobrador(Cobrador cobrador){

        ContentValues values = new ContentValues();

        try{
            values.put(Coluna.NOME_COMPLETO, cobrador.getNomeCompleto());
            values.put(Coluna.INSTITUICAO, cobrador.getInstituicao());
            values.put(Coluna.CPF, cobrador.getCpf());
            values.put(Coluna.ENDERECO, cobrador.getEndereco());
            values.put(Coluna.TELEFONE, cobrador.getTelefone());
            values.put(Coluna.SENHA, cobrador.getSenha());
            values.put(Coluna.TIPO_DE_USUARIO, cobrador.getTipoDeUsuario());

            db.insert(Tabela.COBRADOR,null, values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void deletarCobrador(Cobrador cobrador){

        try {
            db.delete(Tabela.COBRADOR, Coluna.ID + "= ?", new String[]{String.valueOf(cobrador.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void atualizarCobrador(Cobrador cobrador){

        ContentValues values = new ContentValues();

        try{
            values.put(Coluna.NOME_COMPLETO, cobrador.getNomeCompleto());
            values.put(Coluna.INSTITUICAO, cobrador.getInstituicao());
            values.put(Coluna.CPF, cobrador.getCpf());
            values.put(Coluna.ENDERECO, cobrador.getEndereco());
            values.put(Coluna.TELEFONE, cobrador.getTelefone());
            values.put(Coluna.SENHA, cobrador.getSenha());

            db.update(Tabela.COBRADOR, values,Coluna.ID + "= ?",new String[]{String.valueOf(cobrador.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

    }

    public List<Cobrador> listarCobrador(int id){
        List<Cobrador> listaDeCobradores = new ArrayList<Cobrador>();
        try {
            String queryListaCobradores = "SELECT * FROM "+Tabela.COBRADOR+";";
            Cursor cursor = db.rawQuery(queryListaCobradores, null);
            if (cursor.moveToFirst()) {
                do {
                    Cobrador cobrador = new Cobrador();
                    cobrador.setId(Integer.parseInt(cursor.getString(0)));
                    cobrador.setNomeCompleto(cursor.getString(1));
                    cobrador.setInstituicao(cursor.getString(2));
                    cobrador.setCpf(cursor.getString(3));
                    cobrador.setEndereco(cursor.getString(4));
                    cobrador.setTelefone(cursor.getString(5));
                    cobrador.setSenha(cursor.getString(6));

                    listaDeCobradores.add(cobrador);
                } while (cursor.moveToNext());
            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaDeCobradores;
    }

    public Cobrador lerCobrador(int id){
        Cobrador cobrador = null;
        try {
            String queryListaCobradores = "SELECT * FROM "+Tabela.COBRADOR+ " WHERE " +Coluna.ID+"=" + String.valueOf(id) + " LIMIT 1";
            Cursor cursor = db.rawQuery(queryListaCobradores, null);
            if (cursor.moveToFirst()) {
                cobrador = new Cobrador();
                cobrador.setId(Integer.parseInt(cursor.getString(0)));
                cobrador.setNomeCompleto(cursor.getString(1));
                cobrador.setInstituicao(cursor.getString(2));
                cobrador.setCpf(cursor.getString(3));
                cobrador.setEndereco(cursor.getString(4));
                cobrador.setTelefone(cursor.getString(5));
                cobrador.setSenha(cursor.getString(6));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return cobrador;
    }

}
