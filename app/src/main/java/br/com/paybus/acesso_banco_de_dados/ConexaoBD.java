package br.com.paybus.acesso_banco_de_dados;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import br.com.paybus.acesso_banco_de_dados.Tabela;
import br.com.paybus.modelo.*;

public class ConexaoBD extends SQLiteOpenHelper{

    private static final String BANCO_DE_DADOS = "bd_paybus";
    private static final int VERSAO = 1;

    public ConexaoBD(Context context) {
        super(context, BANCO_DE_DADOS,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabela.aluno());
        db.execSQL(Tabela.cobrador());
        db.execSQL(Tabela.motorista());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.ALUNO);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.COBRADOR);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.MOTORISTA);
        this.onCreate(db);

    }
    // pay_bus_bd instrucaoSQL

}
