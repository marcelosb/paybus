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
import br.com.paybus.modelo.Motorista;

public class MotoristaDAO {

    private SQLiteDatabase db;

    public MotoristaDAO(Context context) {
        ConexaoBD conexao = new ConexaoBD(context);
        db = conexao.getWritableDatabase();
    }

    public void inserirMotorista(Motorista motorista){

        ContentValues values = new ContentValues();

        try{
            values.put(Coluna.NOME_COMPLETO, motorista.getNomeCompleto());
            values.put(Coluna.CPF, motorista.getCpf());
            values.put(Coluna.ENDERECO, motorista.getEndereco());
            values.put(Coluna.CNH_MOTORISTA, motorista.getCnh());
            values.put(Coluna.TELEFONE, motorista.getTelefone());
            values.put(Coluna.SENHA, motorista.getSenha());
            values.put(Coluna.TIPO_DE_USUARIO, motorista.getTipoDeUsuario());

            db.insert(Tabela.MOTORISTA,null, values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void deletarMotorista(Motorista motorista){

        try {
            db.delete(Tabela.MOTORISTA, Coluna.ID + "= ?", new String[]{String.valueOf(motorista.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void atualizarMotorista(Motorista motorista){

        ContentValues values = new ContentValues();

        try{
            values.put(Coluna.NOME_COMPLETO, motorista.getNomeCompleto());
            values.put(Coluna.CPF, motorista.getCpf());
            values.put(Coluna.ENDERECO, motorista.getEndereco());
            values.put(Coluna.CNH_MOTORISTA, motorista.getCnh());
            values.put(Coluna.TELEFONE, motorista.getTelefone());
            values.put(Coluna.SENHA, motorista.getSenha());

            db.update(Tabela.MOTORISTA, values,Coluna.ID + "= ?",new String[]{String.valueOf(motorista.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

    }

    public List<Motorista> listarMotorista(int id){
        List<Motorista> listaDeMotoristas = new ArrayList<Motorista>();
        try {
            String queryListaMotorista = "SELECT * FROM "+Tabela.MOTORISTA+";";
            Cursor cursor = db.rawQuery(queryListaMotorista, null);
            if (cursor.moveToFirst()) {
                do {
                    Motorista motorista = new Motorista();
                    motorista.setId(Integer.parseInt(cursor.getString(0)));
                    motorista.setNomeCompleto(cursor.getString(1));
                    motorista.setCpf(cursor.getString(2));
                    motorista.setEndereco(cursor.getString(3));
                    motorista.setCnh(cursor.getString(4));
                    motorista.setTelefone(cursor.getString(5));
                    motorista.setSenha(cursor.getString(6));

                    listaDeMotoristas.add(motorista);
                } while (cursor.moveToNext());
            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaDeMotoristas;
    }

    public Motorista lerMotorista(int id){
        Motorista motorista = null;
        try {
            String queryListaMotorista = "SELECT * FROM "+Tabela.MOTORISTA+ " WHERE " +Coluna.ID+"=" + String.valueOf(id) + " LIMIT 1";
            Cursor cursor = db.rawQuery(queryListaMotorista, null);
            if (cursor.moveToFirst()) {
                motorista.setId(Integer.parseInt(cursor.getString(0)));
                motorista.setNomeCompleto(cursor.getString(1));
                motorista.setCpf(cursor.getString(2));
                motorista.setEndereco(cursor.getString(3));
                motorista.setCnh(cursor.getString(4));
                motorista.setTelefone(cursor.getString(5));
                motorista.setSenha(cursor.getString(6));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return motorista;
    }

}
