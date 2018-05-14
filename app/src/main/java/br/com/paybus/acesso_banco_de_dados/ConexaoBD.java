package br.com.paybus.acesso_banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoBD extends SQLiteOpenHelper{

    private static final String BANCO_DE_DADOS = "pay_bus_bd";
    private static final Integer VERSAO = 1;

    public ConexaoBD(Context context) {
        super(context, BANCO_DE_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String instrucaoSQL = "CREATE TABLE tabela_alunos (" +
          //      "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            //    "nome TEXT NOT NULL, sobrenome TEXT NOT NULL," +
              //  "cpf TEXT NOT NULL, celular TEXT NOT NULL, endereco TEXT NOT NULL );";
        //db.execSQL(instrucaoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        * String instrucaoSQL = "DROP TABLE IF EXISTS tabela_alunos";
        * db.execSQL(instrucaoSQL);
        *
        *
        * */
    }
    // pay_bus_bd instrucaoSQL
}
