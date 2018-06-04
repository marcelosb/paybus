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
import br.com.paybus.modelo.Administrador;

public class AdminDAO {

    public SQLiteDatabase db;

    public AdminDAO(Context context) {
        ConexaoBD conexao = new ConexaoBD(context);
        db = conexao.getWritableDatabase();
    }

    public boolean fazerLogin(String campoEmail, String campoSenha){
        Administrador administrador = null;
        try {
            Cursor cursor = db.rawQuery( "SELECT * FROM "+ Tabela.ADMINISTRADOR +" WHERE email = ? AND senha = ? ", new String[]{campoEmail, campoSenha});
            //String queryListaAluno = "SELECT * FROM "+Tabela.ALUNO+ " WHERE " +Coluna.EMAIL+"=" + email +" AND "+Coluna.SENHA+"="+ senha + " LIMIT 1";
            //Cursor cursor = db.rawQuery(queryListaAluno, null);
            if (cursor.moveToFirst()) {
                administrador = new Administrador();
                administrador.setId(Integer.parseInt(cursor.getString(0)));
                administrador.setEmail(cursor.getString(1));
                administrador.setSenha(cursor.getString(2));
                administrador.setTipoDeUsuario(cursor.getString(3));
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return false;
    }

    public List<Administrador> selecionarAdmin(){
        Administrador administrador = null;
        List<Administrador> listaAdmin = new ArrayList<>();
        try {
            String queryListaAluno = "SELECT * FROM "+Tabela.ADMINISTRADOR+";";
            Cursor cursor = db.rawQuery(queryListaAluno, null);
            if (cursor.moveToFirst()) {
                do {
                    administrador = new Administrador();
                    administrador.setId(Integer.parseInt(cursor.getString(0)));
                    administrador.setEmail(cursor.getString(1));
                    administrador.setSenha(cursor.getString(2));
                    administrador.setTipoDeUsuario(cursor.getString(3));
                    listaAdmin.add(administrador);
                } while (cursor.moveToNext());
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaAdmin;
    }

    public void atualizarSenhaDoAdministrador(int id, String senha) {
        ContentValues values = new ContentValues();
        try{
            values.put(Coluna.SENHA, senha);
            db.update(Tabela.ADMINISTRADOR, values,Coluna.ID + "= ? ",new String[]{ String.valueOf(id) });
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }
}
