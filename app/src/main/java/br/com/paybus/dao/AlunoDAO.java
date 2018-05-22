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

public class AlunoDAO {

    public SQLiteDatabase db;

    public AlunoDAO(Context context) {
        ConexaoBD conexao = new ConexaoBD(context);
        db = conexao.getWritableDatabase();
    }

    public void inserirAluno(Aluno aluno){

        ContentValues values = new ContentValues();

        try{
            values.put(Coluna.NOME_COMPLETO, aluno.getNomeCompleto());
            values.put(Coluna.INSTITUICAO, aluno.getInstituicao());
            values.put(Coluna.CPF, aluno.getCpf());
            values.put(Coluna.ENDERECO, aluno.getEndereco());
            values.put(Coluna.TELEFONE, aluno.getTelefone());
            values.put(Coluna.SENHA, aluno.getSenha());
            values.put(Coluna.TIPO_DE_USUARIO, aluno.getTipoDeUsuario());
            values.put(Coluna.EMAIL, aluno.getEmail());

            db.insert(Tabela.ALUNO,null, values);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void deletarAluno(Aluno aluno){
        try {
            db.delete(Tabela.ALUNO, Coluna.ID + "= ?", new String[]{String.valueOf(aluno.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public void atualizarAluno(Aluno aluno){

        ContentValues values = new ContentValues();

        try{
            values.put(Coluna.NOME_COMPLETO, aluno.getNomeCompleto());
            values.put(Coluna.INSTITUICAO, aluno.getInstituicao());
            values.put(Coluna.CPF, aluno.getCpf());
            values.put(Coluna.ENDERECO, aluno.getEndereco());
            values.put(Coluna.TELEFONE, aluno.getTelefone());
            values.put(Coluna.EMAIL, aluno.getEmail());

            db.update(Tabela.ALUNO, values,Coluna.ID + "= ?",new String[]{String.valueOf(aluno.getId())});
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

    }

    public List<Aluno> listarAlunos(){
        List<Aluno> listaDeAlunos = new ArrayList<Aluno>();
        try {
            String queryListaAluno = "SELECT * FROM "+Tabela.ALUNO+";";
            Cursor cursor = db.rawQuery(queryListaAluno, null);
            if (cursor.moveToFirst()) {
                do {
                    Aluno aluno = new Aluno();
                    aluno.setId(Integer.parseInt(cursor.getString(0)));
                    aluno.setNomeCompleto(cursor.getString(1));
                    aluno.setInstituicao(cursor.getString(2));
                    aluno.setCpf(cursor.getString(3));
                    aluno.setEndereco(cursor.getString(4));
                    aluno.setTelefone(cursor.getString(5));
                    aluno.setSenha(cursor.getString(6));
                    aluno.setTipoDeUsuario(cursor.getString(7));
                    aluno.setEmail(cursor.getString(8));

                    listaDeAlunos.add(aluno);
                } while (cursor.moveToNext());
            }
        }catch(Exception e ){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return listaDeAlunos;
    }

    public Aluno lerAluno(int id){
        Aluno aluno = null;
        try {
            String queryListaAluno = "SELECT * FROM "+Tabela.ALUNO+ " WHERE " +Coluna.ID+"=" + String.valueOf(id) + " LIMIT 1";
            Cursor cursor = db.rawQuery(queryListaAluno, null);
            if (cursor.moveToFirst()) {
                aluno = new Aluno();
                aluno.setId(Integer.parseInt(cursor.getString(0)));
                aluno.setNomeCompleto(cursor.getString(1));
                aluno.setInstituicao(cursor.getString(2));
                aluno.setCpf(cursor.getString(3));
                aluno.setEndereco(cursor.getString(4));
                aluno.setTelefone(cursor.getString(5));
                aluno.setSenha(cursor.getString(6));
                aluno.setTipoDeUsuario(cursor.getString(7));
                aluno.setEmail(cursor.getString(8));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return aluno;
    }

}
