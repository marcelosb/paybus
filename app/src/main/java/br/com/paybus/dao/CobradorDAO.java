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
            values.put(Coluna.EMAIL, cobrador.getEmail());

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
            values.put(Coluna.EMAIL, cobrador.getEmail());

            db.update(Tabela.COBRADOR, values,Coluna.ID + "= ?",new String[]{String.valueOf(cobrador.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

    }

    public List<Cobrador> listarCobradores(){
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
                    cobrador.setTipoDeUsuario(cursor.getString(7));
                    cobrador.setEmail(cursor.getString(8));

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

    public Cobrador lerCobrador(String  campoEmail, String campoSenha){
        Cobrador cobrador = null;
        try {

            Cursor cursor = db.query(Tabela.COBRADOR, new String[]{Coluna.EMAIL, Coluna.SENHA}, " email = ? AND senha = ?", new String[] { campoEmail,campoSenha  }, null, null, null, null);

            //Cursor cursor = db_leitura.rawQuery(queryListaCobradores, null);
            //String queryListaCobradores = "SELECT * FROM "+Tabela.COBRADOR+ " WHERE " +Coluna.ID+"=" + String.valueOf(id) + " LIMIT 1";
            //Cursor cursor = db_leitura.rawQuery(queryListaCobradores, null);
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

    public boolean fazerLogin(String campoEmail, String campoSenha ){
        Cobrador cobrador = null;
        try {
            //Cursor cursor = db_leitura.query(Tabela.COBRADOR, new String[]{Coluna.EMAIL, Coluna.SENHA}, " email = ? AND senha = ?", new String[] { campoEmail,campoSenha  }, null, null, null, null);
            Cursor cursor = db.rawQuery( "SELECT * FROM "+Tabela.COBRADOR +" WHERE "+ Coluna.EMAIL+"= ? AND " +Coluna.SENHA+"= ? ;", new String[]{campoEmail, campoSenha});
            //String queryListaAluno = "SELECT * FROM "+Tabela.COBRADOR+ " WHERE " +Coluna.EMAIL+"=" + email +" AND "+Coluna.SENHA+"="+ senha + " LIMIT 1";
            //Cursor cursor = db.rawQuery(queryListaAluno, null);
            if (cursor.moveToFirst()) {
                cobrador = new Cobrador();
                cobrador.setId(Integer.parseInt(cursor.getString(0)));
                cobrador.setNomeCompleto(cursor.getString(1));
                cobrador.setInstituicao(cursor.getString(2));
                cobrador.setCpf(cursor.getString(3));
                cobrador.setEndereco(cursor.getString(4));
                cobrador.setTelefone(cursor.getString(5));
                cobrador.setSenha(cursor.getString(6));
                cobrador.setTipoDeUsuario(cursor.getString(7));
                cobrador.setEmail(cursor.getString(8));
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return false;
    }

    public void atualizarSenhaDoCobrador(int id ,String senha){
        ContentValues values = new ContentValues();
        try{
            values.put(Coluna.SENHA, senha);
            db.update(Tabela.COBRADOR, values,Coluna.ID + "= ? ",new String[]{ String.valueOf(id) });
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

}
